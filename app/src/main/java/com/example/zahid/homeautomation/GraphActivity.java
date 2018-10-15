package com.example.zahid.homeautomation;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.zahid.homeautomation.Model.Month;
import com.example.zahid.homeautomation.Utill.Common;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GraphActivity extends AppCompatActivity {

    //Firebase
    DatabaseReference monthTable;
    DatabaseReference moUnitRef;
    ListView lv;
    ArrayList<BarData> list;
    static List<String> monthList;
    private List<String> months;
    private ChartDataAdapter chartDataAdapter;
    List<String> fMonth = new ArrayList<>();
    List<String> lMonth = new ArrayList<>();
    LottieAnimationView lav_loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        lv = (ListView) findViewById(R.id.lv_graph);
        lav_loading = (LottieAnimationView) findViewById(R.id.lav_loading);
        monthTable = FirebaseDatabase.getInstance().getReference(Common.STR_Month);
        moUnitRef = monthTable.child(Common.STR_Units);
        monthList = new ArrayList<>();

        list = new ArrayList<>();

    }

    @Override
    protected void onStart() {
        super.onStart();
        fetchMonthData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (chartDataAdapter != null) {
            chartDataAdapter.clear();
        }
    }

    private class ChartDataAdapter extends ArrayAdapter<BarData> {

        public ChartDataAdapter(Context context, List<BarData> objects) {
            super(context, 0, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            BarData data = getItem(position);

            if (Common.monthSizeExceed) {
                if (Common.month == 0) {
                    months = fMonth;
                    Common.month = 1;
                } else if (Common.month == 1) {
                    months = lMonth;
                    Common.month = 0;
                }
            }

            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();

                convertView = getLayoutInflater().from(getContext()).inflate(R.layout.list_item_barchart, null);
                holder.chart = (BarChart) convertView.findViewById(R.id.chart);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            if (data != null) {
                data.setValueTextColor(Color.BLACK);
            }

            holder.chart.getDescription().setEnabled(false);
            holder.chart.setDrawGridBackground(false);

            XAxis xAxis = holder.chart.getXAxis();
            xAxis.setValueFormatter(new MyXAxisValueFormatter(months));
            xAxis.setGranularity(1);
            xAxis.setTextSize(12);

            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setDrawGridLines(false);

            YAxis leftAxis = holder.chart.getAxisLeft();
            leftAxis.setLabelCount(5, false);
            leftAxis.setSpaceTop(15f);
            leftAxis.setTextSize(10);


            YAxis rightAxis = holder.chart.getAxisRight();
            rightAxis.setLabelCount(5, false);
            rightAxis.setSpaceTop(15f);
            rightAxis.setTextSize(10);


            holder.chart.setData(data);
            holder.chart.setFitBars(true);

            holder.chart.animateY(500);

            return convertView;
        }

        private class ViewHolder {
            BarChart chart;
        }
    }

    private BarData generateData(int year) {
        ArrayList<BarEntry> entries = new ArrayList<>();
        ArrayList<BarEntry> barEntries1 = new ArrayList<>();

        for (int i = 0; i <= monthList.size() - 1; i++) {
//            entries.add(new BarEntry(i, (float) (Math.random() * 70) + 30));
            entries.add(new BarEntry(i, Float.parseFloat(monthList.get(i))));
        }

        BarDataSet barDataSet = new BarDataSet(entries, "Year: " + year);
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setBarShadowColor(Color.rgb(203, 203, 203));

        BarData data = new BarData(barDataSet);
        data.setBarWidth(0.9f);
        return data;
    }

    public class MyXAxisValueFormatter implements IAxisValueFormatter {

        private List<String> mValues;

        public MyXAxisValueFormatter(List<String> values) {
            this.mValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mValues.get((int) value);
        }
    }


    private void fetchMonthData() {
        monthTable.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                lav_loading.setVisibility(View.GONE);
                monthList.clear();
                months = new ArrayList<>();
                if (chartDataAdapter != null) {
                    chartDataAdapter.clear();
                }
                for (DataSnapshot monthDataSnapShot : dataSnapshot.getChildren()) {
                    Month monthData = monthDataSnapShot.getValue(Month.class);
                    if (monthData != null) {
                        monthList.add(monthData.getMounits());
                    }
                    if (monthData != null) {
                        months.add(monthData.getMonth());
                    }
//                    Log.i("month", monthData.getMounits());
                }
                if (months.size() <= 6) {
                    list.add(generateData(Calendar.getInstance().get(Calendar.YEAR)));
                    Common.monthSizeExceed = false;
                } else {
                    Common.monthSizeExceed = true;
                    List<String> fMonthunits = new ArrayList<>();
                    List<String> lMonthunits = new ArrayList<>();

                    for (int i = 0; i <= 5; i++) {
                        fMonth.add(months.get(i));
                        fMonthunits.add(monthList.get(i));
                    }
                    for (int i = 6; i <= months.size() - 1; i++) {
                        lMonth.add(months.get(i));
                        lMonthunits.add(monthList.get(i));
                    }
                    for (int i = 0; i <= 1; i++) {
                        if (i == 0) {
                            monthList = fMonthunits;
                            list.add(generateData(Calendar.getInstance().get(Calendar.YEAR)));
                        } else {
                            monthList = lMonthunits;
                            list.add(generateData(Calendar.getInstance().get(Calendar.YEAR)));
                        }

                    }
                }
                chartDataAdapter = new ChartDataAdapter(getApplicationContext(), list);
                lv.setAdapter(chartDataAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(GraphActivity.this, (CharSequence) databaseError, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

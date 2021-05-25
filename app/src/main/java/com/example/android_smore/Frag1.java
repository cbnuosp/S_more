package com.example.android_smore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;
import java.util.Date;

public class Frag1 extends Fragment {
    private static MaterialCalendarView materialCalendarView;
    private View view;

    //material calendarview로 달력커스텀
    public void onViewCreated( final View view, @androidx.annotation.Nullable Bundle savedInstanceState ) {
        super.onViewCreated(view, savedInstanceState);
        materialCalendarView = getView().findViewById(R.id.calendarView);

        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2010, 1, 1))
                .setMaximumDate(CalendarDay.from(2030, 12, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();


        materialCalendarView.addDecorators(
                new SundayDecorator(), //일요일 색 설정
                new SaturdayDecorator(), //토요일 색 설정
                new todayDecorator() //오늘 색 설정
        );
    }

    //버튼클릭시 Todo화면으로 넘어감
    public View onCreateView( @NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag1, container, false);

        ImageButton todoButton;
        todoButton=(ImageButton)view.findViewById(R.id.todoButton);
        todoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                getFragmentManager().beginTransaction().replace(R.id.main_frame, new Frag1_1()).commit();
            }
        });
        return view;
    }
    //일요일 색상 변경
    private class SundayDecorator implements DayViewDecorator {
        private final Calendar calendar = Calendar.getInstance();

        private SundayDecorator() {
        }

        @Override
        public boolean shouldDecorate( CalendarDay day ) {
            day.copyTo(calendar);
            int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
            return weekDay == Calendar.SUNDAY;
        }

        @Override
        public void decorate( DayViewFacade view ) {
            view.addSpan(new ForegroundColorSpan(Color.RED));
        }
    }

    //토요일 색상 변경
    private class SaturdayDecorator implements DayViewDecorator {
        private final Calendar calendar = Calendar.getInstance();

        private SaturdayDecorator() {
        }

        @Override
        public boolean shouldDecorate( CalendarDay day ) {
            day.copyTo(calendar);
            int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
            return weekDay == Calendar.SATURDAY;
        }

        @Override
        public void decorate( DayViewFacade view ) {
            view.addSpan(new ForegroundColorSpan(Color.BLUE));
        }
    }

    //오늘 색상 변경
    private class todayDecorator implements DayViewDecorator {
        private CalendarDay date;

        private todayDecorator() {
            date = CalendarDay.today();
        }

        @Override
        public boolean shouldDecorate( CalendarDay day ) {
            return date != null && day.equals(date);
        }

        @Override
        public void decorate( DayViewFacade view ) {
            view.addSpan(new StyleSpan(Typeface.BOLD));
            view.addSpan(new RelativeSizeSpan(1.4f));
            view.addSpan(new ForegroundColorSpan(Color.parseColor("#9370DB")));
        }
        public void setDate(Date date){
            this.date = CalendarDay.from(date);
        }
    }
}
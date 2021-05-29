package com.example.android_smore;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Switch;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.app.AlarmManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import static android.content.Context.MODE_PRIVATE;


public class Frag4 extends Fragment {
    private View view;
    private TextView idtxt;
    private TextView logout;
    String idtext;

    FirebaseFirestore db;
    private DocumentReference docRef;
    private static final String TAG = "DocSnippets";
    //Firebase로 로그인한 사용자 정보 알기 위해
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view= (ViewGroup)inflater.inflate(R.layout.frag4, container, false);
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();
        idtxt = (TextView)view.findViewById(R.id.userid);
        logout = (TextView)view.findViewById(R.id.logoutbtn);
        idtext = user.getEmail();
        idtxt.setText(idtext);

        LinearLayout clayout;
        clayout = (LinearLayout)view.findViewById(R.id.timelayout);
        clayout.setVisibility(View.INVISIBLE);

        final TimePicker picker = (TimePicker)view.findViewById(R.id.timePicker);
        picker.setIs24HourView(true);

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("daily alarm", MODE_PRIVATE);
        long millis = sharedPreferences.getLong("nextNotifyTime", Calendar.getInstance().getTimeInMillis());

        Calendar nextNotifyTime = new GregorianCalendar();
        nextNotifyTime.setTimeInMillis(millis);

        Date currentTime = nextNotifyTime.getTime();
        SimpleDateFormat HourFormat = new SimpleDateFormat("kk", Locale.getDefault());
        SimpleDateFormat MinuteFormat = new SimpleDateFormat("mm", Locale.getDefault());

        int pre_hour = Integer.parseInt(HourFormat.format(currentTime));
        int pre_minute = Integer.parseInt(MinuteFormat.format(currentTime));

        if(Build.VERSION.SDK_INT >= 23) {
            picker.setHour(pre_hour);
            picker.setMinute(pre_minute);
        }
        else {
            picker.setCurrentHour(pre_hour);
            picker.setCurrentMinute(pre_minute);
        }

        Switch switchbtn = (Switch)view.findViewById(R.id.switch1);

        switchbtn.setOnCheckedChangeListener(new OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    clayout.setVisibility(View.VISIBLE);
                } else {
                    clayout.setVisibility(View.INVISIBLE);
                    Calendar calendar = Calendar.getInstance();
                    dailyNotification(calendar, false);
                }
            }
        });

        Button timeButton = (Button)view.findViewById(R.id.setbtn);
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour, hour_24, minute;
                String am_pm;
                if(Build.VERSION.SDK_INT>=23) {
                    hour_24 = picker.getHour();
                    minute = picker.getMinute();
                } else {
                    hour_24 = picker.getCurrentHour();
                    minute = picker.getCurrentMinute();
                }

                if(hour_24 > 12) {
                    am_pm = "PM";
                    hour = hour_24 - 12;
                } else {
                    hour = hour_24;
                    am_pm = "AM";
                }

                //현재 지정된 시간으로 알람 시간 설정
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, hour_24);
                calendar.set(Calendar.MINUTE, minute);
                calendar.set(Calendar.SECOND, 0);

                //이미 지난 시간을 지정했다면 다음날 같은 시간으로 설정
                if(calendar.before(Calendar.getInstance())) {
                    calendar.add(Calendar.DATE, 1);
                }

                Date currentDateTime = calendar.getTime();

                //Preference에 설정한 값 저장
                SharedPreferences.Editor editor = getActivity().getSharedPreferences("daily alarm", MODE_PRIVATE).edit();
                editor.putLong("nextNotifyTime",(long)calendar.getTimeInMillis());
                editor.apply();

                dailyNotification(calendar, true);
            }
        });

        Button pageButton = (Button)view.findViewById(R.id.pageButton);
        pageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getActivity(), Frag4_1.class);
                startActivity(mIntent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent lIntent = new Intent(getActivity(), LoginActivity.class);
                startActivity(lIntent);
            }
        });

        return view;
    }

    void dailyNotification(Calendar calendar, Boolean dnotify) {


        PackageManager pm = this.getActivity().getPackageManager();
        ComponentName receiver = new ComponentName(this.getActivity(), DeviceBootReceiver.class);
        Intent alarmIntent = new Intent(this.getActivity(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getActivity(), 0, alarmIntent, 0);
        AlarmManager alarmManager = (AlarmManager)this.getActivity().getSystemService(Context.ALARM_SERVICE);

        if(dnotify) {
            if(alarmManager != null) {
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                }
            }

            //부팅 후 실행되는 리시버 사용 가능하게 설정
            pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
        }
        else {
            alarmManager.cancel(pendingIntent);
            pm.setComponentEnabledSetting(receiver,
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP);
        }
    }


}
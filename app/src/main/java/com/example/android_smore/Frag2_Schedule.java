package com.example.android_smore;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import static android.content.ContentValues.TAG;

public class Frag2_Schedule {

    private String monday[] = new String[10];
    private String tuesday[] = new String[10];
    private String wednesday[] = new String[10];
    private String thursday[] = new String[10];
    private String friday[] = new String[10];

    public Frag2_Schedule(){
        for(int i=1;i<10;i++){
            monday[i]="";
            tuesday[i]="";
            wednesday[i]="";
            thursday[i]="";
            friday[i]="";
        }
    }

    public void addSchedule(String scheduleText){
        int temp;
        String tempstr=scheduleText;
        // 월 기준으로 파싱
        if((temp=scheduleText.indexOf("월요일"))>-1)
        {
            temp+=2;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp; i<scheduleText.length() && scheduleText.charAt(i)!=':';i++)
            {
                if(scheduleText.charAt(i)=='['){
                    startPoint=i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint=i;
                    monday[Integer.parseInt(scheduleText.substring(startPoint)+1,endPoint)]="과목";
                }
            }
        }

        if((temp=scheduleText.indexOf("화요일"))>-1)
        {
            temp+=2;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp; i<scheduleText.length() && scheduleText.charAt(i)!=':';i++)
            {
                if(scheduleText.charAt(i)=='['){
                    startPoint=i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint=i;
                    tuesday[Integer.parseInt(scheduleText.substring(startPoint)+1,endPoint)]="과목";
                }
            }
        }

        if((temp=scheduleText.indexOf("수요일"))>-1)
        {
            temp+=2;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp; i<scheduleText.length() && scheduleText.charAt(i)!=':';i++)
            {
                if(scheduleText.charAt(i)=='['){
                    startPoint=i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint=i;
                    wednesday[Integer.parseInt(scheduleText.substring(startPoint)+1,endPoint)]="과목";
                }
            }
        }

        if((temp=scheduleText.indexOf("목요일"))>-1)
        {
            temp+=2;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp; i<scheduleText.length() && scheduleText.charAt(i)!=':';i++)
            {
                if(scheduleText.charAt(i)=='['){
                    startPoint=i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint=i;
                    thursday[Integer.parseInt(scheduleText.substring(startPoint)+1,endPoint)]="과목";
                }
            }
        }

        if((temp=scheduleText.indexOf("금요일"))>-1)
        {
            temp+=2;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp; i<scheduleText.length() && scheduleText.charAt(i)!=':';i++)
            {
                if(scheduleText.charAt(i)=='['){
                    startPoint=i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint=i;
                    friday[Integer.parseInt(scheduleText.substring(startPoint)+1,endPoint)]="과목";
                }
            }
        }

    }

    public boolean validate(String scheduleText){
        if(scheduleText.equals(""))
        {
            return true;
        }
        int temp;
        if((temp=scheduleText.indexOf("월요일"))>-1)
        {
            temp+=2;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp; i<scheduleText.length() && scheduleText.charAt(i)!=':';i++)
            {
                if(scheduleText.charAt(i)=='['){
                    startPoint=i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint=i;
                    if(!monday[Integer.parseInt(scheduleText.substring(startPoint)+1,endPoint)].equals(""))
                    {
                        Log.d(TAG,"시간표 파일 중복");
                        return false;
                    }
                }
            }
        }
        if((temp=scheduleText.indexOf("화요일"))>-1)
        {
            temp+=2;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp; i<scheduleText.length() && scheduleText.charAt(i)!=':';i++)
            {
                if(scheduleText.charAt(i)=='['){
                    startPoint=i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint=i;
                    if(!tuesday[Integer.parseInt(scheduleText.substring(startPoint)+1,endPoint)].equals(""))
                    {
                        Log.d(TAG,"시간표 파일 중복");
                        return false;
                    }
                }
            }
        }
        if((temp=scheduleText.indexOf("수요일"))>-1)
        {
            temp+=2;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp; i<scheduleText.length() && scheduleText.charAt(i)!=':';i++)
            {
                if(scheduleText.charAt(i)=='['){
                    startPoint=i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint=i;
                    if(!wednesday[Integer.parseInt(scheduleText.substring(startPoint)+1,endPoint)].equals(""))
                    {
                        Log.d(TAG,"시간표 파일 중복");
                        return false;
                    }
                }
            }
        }
        if((temp=scheduleText.indexOf("목요일"))>-1)
        {
            temp+=2;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp; i<scheduleText.length() && scheduleText.charAt(i)!=':';i++)
            {
                if(scheduleText.charAt(i)=='['){
                    startPoint=i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint=i;
                    if(!thursday[Integer.parseInt(scheduleText.substring(startPoint)+1,endPoint)].equals(""))
                    {
                        Log.d(TAG,"시간표 파일 중복");
                        return false;
                    }
                }
            }
        }
        if((temp=scheduleText.indexOf("금요일"))>-1)
        {
            temp+=2;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp; i<scheduleText.length() && scheduleText.charAt(i)!=':';i++)
            {
                if(scheduleText.charAt(i)=='['){
                    startPoint=i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint=i;
                    if(!friday[Integer.parseInt(scheduleText.substring(startPoint)+1,endPoint)].equals(""))
                    {
                        Log.d(TAG,"시간표 파일 중복");
                        return false;
                    }
                }
            }
        }
        return true;
    }


    public void setting(TextView[] monday, TextView[] tuesday, TextView[] wednesday, TextView[] thursday, TextView[] friday, Context context){
        for(int i=1;i<10;i++)
        {
            if(!this.monday[i].equals(""))
            {
                monday[i].setText(this.monday[i]);
                monday[i].setBackgroundColor(context.getResources().getColor(R.color.LightBlue));
            }
            if(!this.tuesday[i].equals(""))
            {
                tuesday[i].setText(this.monday[i]);
                tuesday[i].setBackgroundColor(context.getResources().getColor(R.color.LightBlue));
            }
            if(!this.wednesday[i].equals(""))
            {
                wednesday[i].setText(this.monday[i]);
                wednesday[i].setBackgroundColor(context.getResources().getColor(R.color.LightBlue));
            }
            if(!this.thursday[i].equals(""))
            {
                thursday[i].setText(this.monday[i]);
                thursday[i].setBackgroundColor(context.getResources().getColor(R.color.LightBlue));
            }
            if(!this.friday[i].equals(""))
            {
                friday[i].setText(this.monday[i]);
                friday[i].setBackgroundColor(context.getResources().getColor(R.color.LightBlue));
            }
        }
    }

}

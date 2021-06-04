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
        for(int i=0;i<10;i++){
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
            temp+=4;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp; i<scheduleText.length();i++)
            {
                if(scheduleText.charAt(i)=='['){
                    startPoint=i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint=i;
                    monday[Integer.parseInt(scheduleText.substring((startPoint)+1,endPoint))]="과목";
                }
            }
        }

        if((temp=scheduleText.indexOf("화요일"))>-1)
        {
            temp+=4;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp; i<scheduleText.length();i++)
            {
                if(scheduleText.charAt(i)=='['){
                    startPoint=i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint=i;
                    tuesday[Integer.parseInt(scheduleText.substring((startPoint)+1,endPoint))]="과목";
                }
            }
        }

        if((temp=scheduleText.indexOf("수요일"))>-1)
        {
            temp+=4;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp; i<scheduleText.length();i++)
            {
                if(scheduleText.charAt(i)=='['){
                    startPoint=i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint=i;
                    wednesday[Integer.parseInt(scheduleText.substring((startPoint)+1,endPoint))]="과목";
                }
            }
        }

        if((temp=scheduleText.indexOf("목요일"))>-1)
        {
            temp+=4;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp; i<scheduleText.length();i++)
            {
                if(scheduleText.charAt(i)=='['){
                    startPoint=i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint=i;
                    thursday[Integer.parseInt(scheduleText.substring((startPoint)+1,endPoint))]="과목";
                }
            }
        }

        if((temp=scheduleText.indexOf("금요일"))>-1)
        {
            temp+=4;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp; i<scheduleText.length();i++)
            {
                if(scheduleText.charAt(i)=='['){
                    startPoint=i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint=i;
                    friday[Integer.parseInt(scheduleText.substring((startPoint)+1,endPoint))]="과목";
                }
            }
        }

    }

    public void addSchedule(String scheduleText, String courseTitle, String courseProfessor,String courseColor){
        String professor;
        if(courseProfessor.equals("")){
            professor="";
        }
        else{
            professor="("+courseProfessor+")";
        }
        int temp;
        String tempstr=scheduleText;

        if((temp=scheduleText.indexOf("월요일"))>-1)
        {
            temp+=4;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp; i<scheduleText.length();i++)
            {
                if(scheduleText.charAt(i)=='['){
                    startPoint=i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint=i;
                    monday[Integer.parseInt(scheduleText.substring((startPoint)+1,endPoint))]=courseTitle+professor+":"+courseColor;
                }
            }
        }

        if((temp=scheduleText.indexOf("화요일"))>-1)
        {
            temp+=4;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp; i<scheduleText.length();i++)
            {
                if(scheduleText.charAt(i)=='['){
                    startPoint=i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint=i;
                    tuesday[Integer.parseInt(scheduleText.substring((startPoint)+1,endPoint))]=courseTitle+professor+":"+courseColor;
                }
            }
        }

        if((temp=scheduleText.indexOf("수요일"))>-1)
        {
            temp+=4;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp; i<scheduleText.length();i++)
            {
                if(scheduleText.charAt(i)=='['){
                    startPoint=i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint=i;
                    wednesday[Integer.parseInt(scheduleText.substring((startPoint)+1,endPoint))]=courseTitle+professor+":"+courseColor;
                }
            }
        }

        if((temp=scheduleText.indexOf("목요일"))>-1)
        {
            temp+=4;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp; i<scheduleText.length();i++)
            {
                if(scheduleText.charAt(i)=='['){
                    startPoint=i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint=i;
                    thursday[Integer.parseInt(scheduleText.substring((startPoint)+1,endPoint))]=courseTitle+professor+":"+courseColor;
                }
            }
        }

        if((temp=scheduleText.indexOf("금요일"))>-1)
        {
            temp+=4;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp; i<scheduleText.length();i++)
            {
                if(scheduleText.charAt(i)=='['){
                    startPoint=i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint=i;
                    friday[Integer.parseInt(scheduleText.substring((startPoint)+1,endPoint))]=courseTitle+professor+":"+courseColor;
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
            temp+=4;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp; i<scheduleText.length();i++)
            {
                if(scheduleText.charAt(i)=='['){
                    startPoint=i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint=i;
                    if(!monday[Integer.parseInt(scheduleText.substring((startPoint)+1,endPoint))].equals(""))
                    {
                        Log.d(TAG,"시간표 파일 중복");
                        return false;
                    }
                }
            }
        }
        if((temp=scheduleText.indexOf("화요일"))>-1)
        {
            temp+=4;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp; i<scheduleText.length();i++)
            {
                if(scheduleText.charAt(i)=='['){
                    startPoint=i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint=i;
                    if(!tuesday[Integer.parseInt(scheduleText.substring((startPoint)+1,endPoint))].equals(""))
                    {
                        Log.d(TAG,"시간표 파일 중복");
                        return false;
                    }
                }
            }
        }
        if((temp=scheduleText.indexOf("수요일"))>-1)
        {
            temp+=4;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp; i<scheduleText.length();i++)
            {
                if(scheduleText.charAt(i)=='['){
                    startPoint=i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint=i;
                    if(!wednesday[Integer.parseInt(scheduleText.substring((startPoint)+1,endPoint))].equals(""))
                    {
                        Log.d(TAG,"시간표 파일 중복");
                        return false;
                    }
                }
            }
        }
        if((temp=scheduleText.indexOf("목요일"))>-1)
        {
            temp+=4;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp; i<scheduleText.length();i++)
            {
                if(scheduleText.charAt(i)=='['){
                    startPoint=i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint=i;
                    if(!thursday[Integer.parseInt(scheduleText.substring((startPoint)+1,endPoint))].equals(""))
                    {
                        Log.d(TAG,"시간표 파일 중복");
                        return false;
                    }
                }
            }
        }
        if((temp=scheduleText.indexOf("금요일"))>-1)
        {
            temp+=4;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp; i<scheduleText.length();i++)
            {
                if(scheduleText.charAt(i)=='['){
                    startPoint=i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint=i;
                    if(!friday[Integer.parseInt(scheduleText.substring((startPoint)+1,endPoint))].equals(""))
                    {
                        Log.d(TAG,"시간표 파일 중복");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void setting(Frag2_1_AutoResizeTextView[] monday, Frag2_1_AutoResizeTextView[] tuesday, Frag2_1_AutoResizeTextView[] wednesday, Frag2_1_AutoResizeTextView[] thursday, Frag2_1_AutoResizeTextView[] friday, Context context){
        int index;
        String coursetext;
        String colortext;

        int maxLength=0;
        String maxString="";
        for(int i=0;i<10;i++){
            if(this.monday[i].length()>maxLength) {
                maxLength=this.monday[i].length();
                maxString=this.monday[i];
            }
            if(this.tuesday[i].length()>maxLength) {
                maxLength=this.tuesday[i].length();
                maxString=this.tuesday[i];
            }
            if(this.wednesday[i].length()>maxLength) {
                maxLength=this.wednesday[i].length();
                maxString=this.wednesday[i];
            }
            if(this.thursday[i].length()>maxLength) {
                maxLength=this.thursday[i].length();
                maxString=this.thursday[i];
            }
            if(this.friday[i].length()>maxLength) {
                maxLength=this.friday[i].length();
                maxString=this.friday[i];
            }
        }

        for(int i=1;i<10;i++)
        {
            if(!this.monday[i].equals(""))
            {
                index=this.monday[i].indexOf(':');
                coursetext=this.monday[i].substring(0,index);
                colortext=this.monday[i].substring(index+1);
                monday[i].setText(coursetext);

                if(colortext.equals("LightBlue"))
                    monday[i].setBackgroundColor(context.getResources().getColor(R.color.LightBlue));
                if(colortext.equals("LightCoral"))
                    monday[i].setBackgroundColor(context.getResources().getColor(R.color.LightCoral));
                if(colortext.equals("LightCyan"))
                    monday[i].setBackgroundColor(context.getResources().getColor(R.color.LightCyan));
                if(colortext.equals("LightGray"))
                    monday[i].setBackgroundColor(context.getResources().getColor(R.color.LightGray));
                if(colortext.equals("LightGreen"))
                    monday[i].setBackgroundColor(context.getResources().getColor(R.color.LightGreen));
                if(colortext.equals("LightPink"))
                    monday[i].setBackgroundColor(context.getResources().getColor(R.color.LightPink));
                if(colortext.equals("LightSlateGray"))
                    monday[i].setBackgroundColor(context.getResources().getColor(R.color.LightSlateGray));
                if(colortext.equals("LightYellow"))
                    monday[i].setBackgroundColor(context.getResources().getColor(R.color.LightYellow));
                if(colortext.equals("LightSteelBlue"))
                    monday[i].setBackgroundColor(context.getResources().getColor(R.color.LightSteelBlue));

            }
            else{
                monday[i].setText(maxString);
            }
            if(!this.tuesday[i].equals(""))
            {
                index=this.tuesday[i].indexOf(':');
                coursetext=this.tuesday[i].substring(0,index);
                colortext=this.tuesday[i].substring(index+1);
                tuesday[i].setText(coursetext);

                if(colortext.equals("LightBlue"))
                    tuesday[i].setBackgroundColor(context.getResources().getColor(R.color.LightBlue));
                if(colortext.equals("LightCoral"))
                    tuesday[i].setBackgroundColor(context.getResources().getColor(R.color.LightCoral));
                if(colortext.equals("LightCyan"))
                    tuesday[i].setBackgroundColor(context.getResources().getColor(R.color.LightCyan));
                if(colortext.equals("LightGray"))
                    tuesday[i].setBackgroundColor(context.getResources().getColor(R.color.LightGray));
                if(colortext.equals("LightGreen"))
                    tuesday[i].setBackgroundColor(context.getResources().getColor(R.color.LightGreen));
                if(colortext.equals("LightPink"))
                    tuesday[i].setBackgroundColor(context.getResources().getColor(R.color.LightPink));
                if(colortext.equals("LightSlateGray"))
                    tuesday[i].setBackgroundColor(context.getResources().getColor(R.color.LightSlateGray));
                if(colortext.equals("LightYellow"))
                    tuesday[i].setBackgroundColor(context.getResources().getColor(R.color.LightYellow));
                if(colortext.equals("LightSteelBlue"))
                    tuesday[i].setBackgroundColor(context.getResources().getColor(R.color.LightSteelBlue));
            }
            else{
                tuesday[i].setText(maxString);
            }
            if(!this.wednesday[i].equals(""))
            {
                index=this.wednesday[i].indexOf(':');
                coursetext=this.wednesday[i].substring(0,index);
                colortext=this.wednesday[i].substring(index+1);
                wednesday[i].setText(coursetext);

                if(colortext.equals("LightBlue"))
                    wednesday[i].setBackgroundColor(context.getResources().getColor(R.color.LightBlue));
                if(colortext.equals("LightCoral"))
                    wednesday[i].setBackgroundColor(context.getResources().getColor(R.color.LightCoral));
                if(colortext.equals("LightCyan"))
                    wednesday[i].setBackgroundColor(context.getResources().getColor(R.color.LightCyan));
                if(colortext.equals("LightGray"))
                    wednesday[i].setBackgroundColor(context.getResources().getColor(R.color.LightGray));
                if(colortext.equals("LightGreen"))
                    wednesday[i].setBackgroundColor(context.getResources().getColor(R.color.LightGreen));
                if(colortext.equals("LightPink"))
                    wednesday[i].setBackgroundColor(context.getResources().getColor(R.color.LightPink));
                if(colortext.equals("LightSlateGray"))
                    wednesday[i].setBackgroundColor(context.getResources().getColor(R.color.LightSlateGray));
                if(colortext.equals("LightYellow"))
                    wednesday[i].setBackgroundColor(context.getResources().getColor(R.color.LightYellow));
                if(colortext.equals("LightSteelBlue"))
                    wednesday[i].setBackgroundColor(context.getResources().getColor(R.color.LightSteelBlue));
            }
            else{
                wednesday[i].setText(maxString);
            }

            if(!this.thursday[i].equals(""))
            {
                index=this.thursday[i].indexOf(':');
                coursetext=this.thursday[i].substring(0,index);
                colortext=this.thursday[i].substring(index+1);
                thursday[i].setText(coursetext);

                if(colortext.equals("LightBlue"))
                    thursday[i].setBackgroundColor(context.getResources().getColor(R.color.LightBlue));
                if(colortext.equals("LightCoral"))
                    thursday[i].setBackgroundColor(context.getResources().getColor(R.color.LightCoral));
                if(colortext.equals("LightCyan"))
                    thursday[i].setBackgroundColor(context.getResources().getColor(R.color.LightCyan));
                if(colortext.equals("LightGray"))
                    thursday[i].setBackgroundColor(context.getResources().getColor(R.color.LightGray));
                if(colortext.equals("LightGreen"))
                    thursday[i].setBackgroundColor(context.getResources().getColor(R.color.LightGreen));
                if(colortext.equals("LightPink"))
                    thursday[i].setBackgroundColor(context.getResources().getColor(R.color.LightPink));
                if(colortext.equals("LightSlateGray"))
                    thursday[i].setBackgroundColor(context.getResources().getColor(R.color.LightSlateGray));
                if(colortext.equals("LightYellow"))
                    thursday[i].setBackgroundColor(context.getResources().getColor(R.color.LightYellow));
                if(colortext.equals("LightSteelBlue"))
                    thursday[i].setBackgroundColor(context.getResources().getColor(R.color.LightSteelBlue));
            }
            else{
                thursday[i].setText(maxString);
            }

            if(!this.friday[i].equals("")) {
                index = this.friday[i].indexOf(':');
                coursetext = this.friday[i].substring(0, index);
                colortext = this.friday[i].substring(index + 1);
                friday[i].setText(coursetext);

                if (colortext.equals("LightBlue"))
                    friday[i].setBackgroundColor(context.getResources().getColor(R.color.LightBlue));
                if (colortext.equals("LightCoral"))
                    friday[i].setBackgroundColor(context.getResources().getColor(R.color.LightCoral));
                if (colortext.equals("LightCyan"))
                    friday[i].setBackgroundColor(context.getResources().getColor(R.color.LightCyan));
                if (colortext.equals("LightGray"))
                    friday[i].setBackgroundColor(context.getResources().getColor(R.color.LightGray));
                if (colortext.equals("LightGreen"))
                    friday[i].setBackgroundColor(context.getResources().getColor(R.color.LightGreen));
                if (colortext.equals("LightPink"))
                    friday[i].setBackgroundColor(context.getResources().getColor(R.color.LightPink));
                if (colortext.equals("LightSlateGray"))
                    friday[i].setBackgroundColor(context.getResources().getColor(R.color.LightSlateGray));
                if (colortext.equals("LightYellow"))
                    friday[i].setBackgroundColor(context.getResources().getColor(R.color.LightYellow));
                if (colortext.equals("LightSteelBlue"))
                    friday[i].setBackgroundColor(context.getResources().getColor(R.color.LightSteelBlue));
            }
            else{
                friday[i].setText(maxString);
            }

            monday[i].resizeText();
            tuesday[i].resizeText();
            wednesday[i].resizeText();
            thursday[i].resizeText();
            friday[i].resizeText();
        }

    }

}

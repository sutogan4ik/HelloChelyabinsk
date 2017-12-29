package ru.brucha.hellochelyabinsk.tools;

import android.content.Context;

import ru.brucha.hellochelyabinsk.R;

/**
 * Created by prog on 28.12.2017.
 */

public class UserColor {


    public static int getUserColor400(Context context, String name){
        int[] colors = context.getResources().getIntArray(R.array.colors400);
        int position = Math.abs(name.hashCode())%colors.length;
        return colors[position];
    }

    public static int getUserColor200(Context context, String name){
        int[] colors = context.getResources().getIntArray(R.array.colors200);
        int position = Math.abs(name.hashCode())%colors.length;
        return colors[position];
    }

    public static int getUserColor50(Context context, String name){
        int[] colors = context.getResources().getIntArray(R.array.colors50);
        int position = Math.abs(name.hashCode())%colors.length;
        return colors[position];
    }
}

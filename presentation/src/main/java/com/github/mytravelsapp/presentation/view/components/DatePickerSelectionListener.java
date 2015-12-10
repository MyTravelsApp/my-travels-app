package com.github.mytravelsapp.presentation.view.components;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.github.mytravelsapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Datepicker listener that controls {@link DatePickerDialog} selection and open.
 *
 * @author fjtorres
 */
public class DatePickerSelectionListener implements DatePickerDialog.OnDateSetListener, View.OnClickListener {

    /**
     * Source context.
     */
    private final Context context;

    /**
     * View to print and load date.
     */
    private final TextView printView;

    /**
     * Format to date.
     */
    private final String dateFormat;

    public DatePickerSelectionListener(final Context pContext, final TextView pPrintView, final String pDateFormat) {
        this.context = pContext;
        this.printView = pPrintView;
        this.dateFormat = pDateFormat;
    }

    /**
     * Controls datepicker selection.
     *
     * @param view
     * @param year
     * @param monthOfYear
     * @param dayOfMonth
     */
    @Override
    public void onDateSet(final DatePicker view, final int year, final int monthOfYear, final int dayOfMonth) {
        final Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, monthOfYear);
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        final SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        printView.setText(sdf.format(cal.getTime()));
    }

    /**
     * Open datepicker dialog.
     *
     * @param v associated view.
     */
    @Override
    public void onClick(View v) {
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        final Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        if (printView.getText() != null && !"".equals(printView.getText())) {
            try {
                cal.setTime(sdf.parse(printView.getText().toString()));
            } catch (final ParseException e) {
                e.printStackTrace();// FIXME DATE PARSE ERROR!!!
            }
        }
        final DatePickerDialog dialog = new DatePickerDialog(context, R.style.AppTheme_DialogTheme, this, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }
}

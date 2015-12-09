package com.github.mytravelsapp.presentation.view.components;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author fjtorres
 */
public class DatePickerSelectionListener implements DatePickerDialog.OnDateSetListener, View.OnClickListener {

    private final Context context;
    private final TextView printView;
    private final String dateFormat;

    public DatePickerSelectionListener (final Context pContext, final TextView pPrintView, final String pDateFormat) {
        this.context = pContext;
        this.printView = pPrintView;
        this.dateFormat = pDateFormat;
    }
    @Override
    public void onDateSet(final DatePicker view, final int year, final int monthOfYear, final int dayOfMonth) {
        final Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, monthOfYear);
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        final SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        printView.setText(sdf.format(cal.getTime()));
    }

    @Override
    public void onClick(View v) {
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        final Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(printView.getText().toString()));
        } catch (final ParseException e) {
            e.printStackTrace();// FIXME DATE PARSE ERROR!!!
        }
        final DatePickerDialog dialog = new DatePickerDialog(context, this, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }
}

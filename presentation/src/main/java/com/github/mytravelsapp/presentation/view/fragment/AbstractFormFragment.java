package com.github.mytravelsapp.presentation.view.fragment;

import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.widget.TextView;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.presentation.presenter.Presenter;
import com.github.mytravelsapp.presentation.view.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Base {@link android.support.v4.app.Fragment} class for all fragments that represent a form in this application.
 *
 * @author fjtorres
 */
public abstract class AbstractFormFragment<V extends View, P extends Presenter<V>> extends AbstractFragment<V, P> {

    protected boolean validateRequiredField(final TextView textView) {
        boolean isValid = true;
        if (isEmpty(textView)) {
            setError(textView, getString(R.string.error_required));
            isValid = false;
        }
        return isValid;
    }

    protected boolean validateDateFormat(final TextView textView) {
        boolean isValid = true;
        if (!isEmpty(textView)) {
            final SimpleDateFormat sdf = new SimpleDateFormat(getString(R.string.conf_date_format));
            try {
                sdf.parse(textView.getText().toString());
            } catch (final ParseException e) {
                setError(textView, getString(R.string.error_date_format));
                isValid = false;
            }
        }

        return isValid;
    }

    private void setError(final TextView textView, final String message) {
        if (textView.getParent() instanceof TextInputLayout && ((TextInputLayout) textView.getParent()).isErrorEnabled()) {
            ((TextInputLayout) textView.getParent()).setError(message);
        } else {
            textView.setError(message);
        }
    }

    private boolean isEmpty(final TextView textView) {
        return textView != null && textView.getText() != null &&
                TextUtils.isEmpty(textView.getText().toString().trim());
    }
}

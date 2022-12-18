package com.indisparte.clienttcp.util;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.indisparte.clienttcp.di.component.ClientApplication;

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
public class DialogFactory {


    /**
     * Mostra una semplice dialog di errore con del testo con un positive button legato ad una azione
     *
     * @param message                     il messaggio da mostrare nella dialog
     * @param positive_text_btn           il testo da far comparire sul positive button
     * @param negative_text_btn           il testo da far comparire sul negative button
     * @param positiveButtonClickListener l'azione da svolgere al click del positive button
     * @param cancelButtonOnClickListener l'azione da svolgere al click del negative button
     * @return
     */
    public static Dialog createSimpleOkCancelDialog(@NonNull String message,
                                                    @NonNull String positive_text_btn,
                                                    @Nullable String negative_text_btn,
                                                    @Nullable DialogInterface.OnClickListener positiveButtonClickListener,
                                                    @Nullable DialogInterface.OnClickListener cancelButtonOnClickListener) {
        final MaterialAlertDialogBuilder alertDialog = new MaterialAlertDialogBuilder(ClientApplication.getContext())
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(positive_text_btn, positiveButtonClickListener)
                .setNegativeButton(negative_text_btn, cancelButtonOnClickListener);
        return alertDialog.create();
    }


    /**
     * Crea una semplice custom load dialog con un indicatore di progresso e un button per terminare l'azione
     *
     * @param message                     il messaggio da affiancare al caricamento
     * @param positive_btn_msg            il messaggio da far comparire sul button
     * @param positiveButtonClickListener l'azione da eseguire alla pressione del button
     * @return progressDialog
     */
    public static ProgressDialog createProgressDialog(@NonNull String message,
                                                      @NonNull String positive_btn_msg,
                                                      @Nullable DialogInterface.OnClickListener positiveButtonClickListener) {
        final ProgressDialog progressDialog = new ProgressDialog(ClientApplication.getContext());
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.setButton(ProgressDialog.BUTTON_POSITIVE,
                positive_btn_msg, positiveButtonClickListener);
        return progressDialog;
    }

}


package com.manriqueweb.mwepgmedia.ui.commons;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.manriqueweb.mwepgmedia.R;
import com.manriqueweb.mwepgmedia.ui.main.fragments.ProgramDetailFragment;
import com.manriqueweb.mwepgmedia.utils.Constants;

public class ProgramSelectedDialog extends DialogFragment {
    //region App Variables
    private static final String LOG_TAG = Constants.STR_LOG_TAG.concat(ProgramSelectedDialog.class.getSimpleName());
    private final ProgramDetailFragment mProgramDetailFragment = ProgramDetailFragment.newInstance();
    private OnProgramSelectedDialogListener mListener = null;
    //endregion

    //region Arguments constants for Variables
    public static final String KEY_PROGRAM_ID = "program_id";
    public static final String DEFAULT_PROGRAM_ID = "na";
    //endregion

    //region dialog main methods
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bArguments = getArguments();
        String program_id = DEFAULT_PROGRAM_ID;
        if(bArguments!=null){
            program_id = bArguments.getString(KEY_PROGRAM_ID, DEFAULT_PROGRAM_ID);
        }
        mProgramDetailFragment.setProgram_id(program_id);
        mProgramDetailFragment.setOnProgramDetailFragmentListener(new ProgramDetailFragment.OnProgramDetailFragmentListener() {
            @Override
            public void onPlaySelected() {
                if(mListener!=null){
                    mListener.onPlaySelected();
                }
                dismiss();
            }

            @Override
            public void onBackSelected() {
                if(mListener!=null){
                    mListener.onBackSelected();
                }
                dismiss();
            }
        });
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.dialog_program_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentHolder, mProgramDetailFragment);
        transaction.commit();
    }
    //endregion

    //region setter methods
    public void setListener(OnProgramSelectedDialogListener listener) {
        mListener = listener;
    }
    //endregion

    //region Listener methods
    public interface OnProgramSelectedDialogListener {
        void onPlaySelected();
        void onBackSelected();
    }
    //endregion
}

package ru.dashkovskajanastassia.notesapp;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

public class NoteFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match

    static final String SELECTED_NOTE = "note";
    Note note;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NoteFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
         requireActivity().getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();


        if(arguments != null){
           // int index = arguments.getInt(ARG_INDEX);
            note = arguments.getParcelable(SELECTED_NOTE);
            TextView text = view.findViewById(R.id.name);
            text.setText(note.getNoteTitle());
           // TypedArray texts = getResources().obtainTypedArray(R.array.notesList);
            //text.setText(Note.getNotes()[index].getNoteTitle());
            Button buttonBack = view.findViewById(R.id.buttonback);
            buttonBack.setOnClickListener(v -> {
                requireActivity().getSupportFragmentManager().popBackStack();
            });
            text.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
              note.setNoteTitle(s.toString());
                    //      Note.getNotes()[index].setNoteTitle(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            TextView tv = view.findViewById(R.id.Description);
        tv.setText(note.getNoteContent());
            //    tv.setText(Note.getNotes()[index].getNoteContent());

        }
    }
    static final String ARG_INDEX = "index";

    public static NoteFragment newInstance(int index){
        NoteFragment fragment = new NoteFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX,index);
        fragment.setArguments(args);
        return fragment;
    }

    public static NoteFragment newInstance(Note note){
        NoteFragment fragment = new NoteFragment();
        Bundle args = new Bundle();
        args.putParcelable(SELECTED_NOTE, note);
        fragment.setArguments(args);
        return fragment;
    }
}











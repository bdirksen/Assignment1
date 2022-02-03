package com.example.diceroll;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.diceroll.db.Game;

public class AddGameFragment extends DialogFragment {
    private EditText gameName;
    private EditText gameRolls;
    private EditText gameSides;
    private OnFragmentInteractionListener listener;


    public interface OnFragmentInteractionListener{
        void onOkPressed(Game newGame);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener){
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.add_game_fragment_layout, null);
        gameName = view.findViewById(R.id.game_name_editText);
        gameRolls = view.findViewById(R.id.game_rolls_editText);
        gameSides = view.findViewById(R.id.game_sides_editText);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Add Game")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name = gameName.getText().toString();
                        String rollsString = gameRolls.getText().toString();
                        String sidesString = gameSides.getText().toString();
                        try{
                            int rolls = Integer.parseInt(rollsString);
                            int sides = Integer.parseInt(sidesString);
                            if(rolls < 4 && rolls > 0)
                                if(sides < 7 && sides > 0){
                                    Game game = new Game(name, rolls, sides);
                                    listener.onOkPressed(game);
                                }
                        }catch(Exception e){
                        }



                    }
                }).create();

    }
}

package num.pad;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Annika on 21.3.2018.
 */

public class NumpadDialogFragment extends DialogFragment{

    Random r = new Random();
    int number1 = r.nextInt(6-1)+ 1;
    int number2 = r.nextInt(5-1)+ 1;

    private View numpadView;

    TextView questionView;
    EditText answerText;
    GridView numpadGrid;

    static final String[] numbers = new String[]{
            "1", "2", "3",
            "4", "5", "6",
            "7", "8", "9",
            "0"};
//    List<Integer> numberList = new ArrayList<>();
//    for (int i = 1; i < 10; i++){
//        numberList.add(i);
//    }

    //määritellään kysymys (questionOnScreen), oikea vastaus (correctAnswer) ja käyttäjän syöttämä vastaus (userAswer)
    String questionOnScreen = "Laske: " + String.valueOf(number1) + " + " + String.valueOf(number2);
    final Integer correctAnswer = number1 + number2;
    Integer userAnswer;
    String finalUserAnswer;


    ChoiceListener AnswerListener;

    public interface ChoiceListener {
        void onDialogNumberClickCorrect(DialogFragment dialog);
        void onDialogNumberClickWrong(DialogFragment dialog);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof ChoiceListener) {
            AnswerListener = (ChoiceListener) context;
        }
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        numpadView = inflater.inflate(R.layout.numpad, null);
        builder.setView(numpadView);

        questionView = numpadView.findViewById(R.id.questionView);
        questionView.setText(questionOnScreen);
        answerText = numpadView.findViewById(R.id.answerView);
        numpadGrid = numpadView.findViewById(R.id.numbadGrid);

        List<Integer> numberList = new ArrayList<>();
        for (int i = 1; i < 10; i++){
            numberList.add(i);
        }
        numpadGrid.setAdapter(new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, numberList));
        numpadGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object selected_button = numpadGrid.getItemAtPosition(position);
                finalUserAnswer = answerText.getText() + (selected_button).toString();

                answerText.setText(finalUserAnswer);
                userAnswer = Integer.parseInt(finalUserAnswer);
//              String selectedItem = parent.getItemAtPosition(position).toString();
//              userAnswer[0] = Integer.parseInt(selectedItem);
//              answerText.setText(userAnswer[0]);
            }
        });

        Button dialogOKButton = numpadView.findViewById(R.id.dialogButtonOK); //lisätty
        dialogOKButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userAnswer == correctAnswer)
                {
                    //vastaus oikein
                    AnswerListener.onDialogNumberClickCorrect(NumpadDialogFragment.this);


                } else {
                    //vastaus väärin
                    AnswerListener.onDialogNumberClickWrong(NumpadDialogFragment.this);
                }

            }
        });
        Button dialogEraseButton = numpadView.findViewById(R.id.dialogButtonERASE); //lisätty
        dialogEraseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if(finalUserAnswer.length() != 0 || finalUserAnswer != null)
//                {
//                    finalUserAnswer = finalUserAnswer.substring(0, finalUserAnswer.length() -1);
//                    answerText.setText(finalUserAnswer);
//                    userAnswer = Integer.parseInt(finalUserAnswer);
//                }
//                else
//                {
//
//                }
            }
        });
        Button dialogEXITButton = numpadView.findViewById(R.id.dialogButtonEXIT); //lisätty
        dialogEXITButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AnswerListener.onDialogNumberClickWrong(NumpadDialogFragment.this);
            }
        });

        return builder.create();
    }
}

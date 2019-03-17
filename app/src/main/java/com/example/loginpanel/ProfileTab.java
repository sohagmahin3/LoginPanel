package com.example.loginpanel;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {

    private EditText edtProfileName,edtProfileBio,edtProfileProfession,edtProfileHobbies,edtProfileSport;
    private Button updateButton;

    public ProfileTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_tab, container, false);
        edtProfileName = view.findViewById(R.id.edtProfileName);
        edtProfileBio = view.findViewById(R.id.edtProfileBio);
        edtProfileProfession = view.findViewById(R.id.edtProfileProfession);
        edtProfileHobbies = view.findViewById(R.id.edtProfileHobbies);
        edtProfileSport = view.findViewById(R.id.edtProfileSport);
        updateButton = view.findViewById(R.id.updateButton);
        final ParseUser currentUser  = ParseUser.getCurrentUser();

        edtProfileName.setText(currentUser.get("ProfileName")+"");
        edtProfileBio.setText(currentUser.get("ProfileBio")+"");
        edtProfileProfession.setText(currentUser.get("ProfileProfession")+"");
        edtProfileHobbies.setText(currentUser.get("ProfileHobbies")+"");
        edtProfileSport.setText(currentUser.get("ProfileSport")+"");

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUser.put("ProfileName",edtProfileName.getText().toString());
                currentUser.put("ProfileBio",edtProfileBio.getText().toString());
                currentUser.put("ProfileProfession",edtProfileProfession.getText().toString());
                currentUser.put("ProfileHobbies",edtProfileHobbies.getText().toString());
                currentUser.put("ProfileSport",edtProfileSport.getText().toString());

                currentUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            FancyToast.makeText(getContext(), "Info Updated! ", FancyToast.SUCCESS, FancyToast.LENGTH_LONG, true).show();
                        }
                        else {
                            FancyToast.makeText(getContext(), e.getMessage(), FancyToast.ERROR, FancyToast.LENGTH_LONG, true).show();

                        }
                    }
                });

            }
        });
        return view;
    }

}

package nyc.c4q.personabe1984.snapmeme;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by c4q-vanice on 5/31/15.
 */
public class TopFragment extends Fragment {

    private static EditText topinput;
    private static EditText bottominput;
    TopInterface creating;

    public interface TopInterface {
        public void meme(String top, String bottom);

        }
    @Override
    public void onAttach(Activity activity){
        try{
        creating = (TopInterface)activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.top_fragment, container, false);

        topinput = (EditText) view.findViewById(R.id.topinput);
        bottominput = (EditText) view.findViewById(R.id.bottominput);
        final Button button = (Button) view.findViewById(R.id.memeify);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                memeClicked(view);
            }
        });
        return view;
    }


    public void memeClicked(View view){
        creating.meme(topinput.getText().toString(), bottominput.getText().toString());
    }
}

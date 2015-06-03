package nyc.c4q.personabe1984.snapmeme;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by c4q-vanice on 5/31/15.
 */
public class BottomFragment extends Fragment {

        private static TextView memetext1;
        private static TextView memetext2;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.bottom_fragment, container, false);

            memetext1 = (TextView) view.findViewById(R.id.memetext1);
            memetext2 = (TextView) view.findViewById(R.id.memetext2);
            return view;
        }

    public void setmeme(String top, String bottom){
        memetext1.setText(top);
        memetext2.setText(bottom);
    }
    }


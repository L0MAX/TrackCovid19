package com.insightnet.trackcovid19.ui.notifications;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.insightnet.trackcovid19.R;

public class NotificationsFragment extends Fragment {
    View rootView;
    ExpandableListView lv;
    private String[] groups;
    private String[][] children;

    public NotificationsFragment() {

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        groups = new String[]{"What are Coronaviruses", "How it spreads", "Signs and Symptoms", "Prevention", "Treatment", "Emergency Numbers"};

        children = new String[][]{
                {"Coronaviruses are a large group of viruses that are common among animals. In rare cases, they are what scientists call zoonotic, meaning they can be transmitted from animals to humans, according to the US Centers for Disease Control and Prevention. It is a dangerous diseases with incubation period between 4-6 days. It is fatal especially for those with weakened immune system, the elderly and the very young. It could also result in Pneumonia and bronchitis."},
                {"Viruses can spread from human contact with animals and also from human to human.\n" +
                        "When it comes to human-to-human transmission of the viruses, often it happens when someone comes into contact with the infected person's secretions. The exposure factors are;\n" +
                        "\n" +
                        "Cough\n" +
                        "Sneeze or\n" +
                        "Handshake\n" +
                        "\n" +
                        "The virus can also be transmitted by touching something an infected person has touched and then touching your mouth, nose or eyes."},
                {"The viruses can make people sick. Coronavirus symptoms include;\n" +
                        "\n" +
                        "Fever\n" +
                        "A runny nose\n" +
                        "Cough\n" +
                        "Sore throat\n" +
                        "Possibly a headache"},
                {"There is no vaccine to protect against this family of viruses. Reduce your risk of infection by\n" +
                        "\n" +
                        "1. Wash your hands with soap and water before touching anything including your eyes, nose and mouth\n" +
                        "2. Cover your mouth and nose when you cough or sneeze\n" +
                        "3. Disinfect the objects and surfaces you touch.\n" +
                        "5. If you are sick, stay home and avoid crowds and contact with others."},
                {"There is no specific treatment. Most of the time, symptoms will go away on their own.\n" +
                        "Doctors can relieve symptoms by prescribing a pain or fever medication.\n" +
                        "A room humidifier or a hot shower can help with a sore throat or cough.\n" +
                        "Drink plenty of fluids\n" +
                        "Get rest and sleep as much as possible.\n" +
                        "If symptoms feel worse than a standard cold, see your doctor"},
                {"If you suspect you have coronavirus or someone you know has, please call the following numbers\n"+
                        "112\n"+
                        "+233 55 843 9868\n" +
                        "+233 50 949 7700"}
        };
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);


        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lv = (ExpandableListView) view.findViewById(R.id.expListView);
        lv.setAdapter(new ExpandableListAdapter(groups, children));
        lv.setGroupIndicator(null);

    }

    public class ExpandableListAdapter extends BaseExpandableListAdapter {

        private final LayoutInflater inf;
        private String[] groups;
        private String[][] children;

        public ExpandableListAdapter(String[] groups, String[][] children) {
            this.groups = groups;
            this.children = children;
            inf = LayoutInflater.from(getActivity());
        }

        @Override
        public int getGroupCount() {
            return groups.length;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return children[groupPosition].length;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groups[groupPosition];
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return children[groupPosition][childPosition];
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

            ViewHolder holder;
            if (convertView == null) {
                convertView = inf.inflate(R.layout.list_item, parent, false);
                holder = new ViewHolder();

                holder.text = (TextView) convertView.findViewById(R.id.lblListItem);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.text.setText(getChild(groupPosition, childPosition).toString());

            return convertView;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                convertView = inf.inflate(R.layout.list_group, parent, false);

                holder = new ViewHolder();
                holder.text = (TextView) convertView.findViewById(R.id.lblListHeader);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.text.setText(getGroup(groupPosition).toString());

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        private class ViewHolder {
            TextView text;
        }
    }
}

package com.example.manas.movieapp.Fragment_Handlers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manas.movieapp.Adapters.HomeFragmentRCVAdapter;
import com.example.manas.movieapp.Info.MovieInfo;
import com.example.manas.movieapp.MainActivity;
import com.example.manas.movieapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manas on 3/23/2015.
 */
public class MainFragmentHandler extends Fragment {
    ViewGroup mRoot;
    FragmentManager fragmentManager;
    List<MovieInfo> movieInfoList = new ArrayList<>();

    RecyclerView recyclerView;

    public MainFragmentHandler(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        movieInfoList = getMovieinfo();
        mRoot = (ViewGroup) v.findViewById(R.id.relativelayout);
        recyclerView.setAdapter(new HomeFragmentRCVAdapter(getActivity(), fragmentManager,movieInfoList,mRoot));


        return v;

    }


    public List<MovieInfo> getMovieinfo() {
        List<MovieInfo> mf = new ArrayList<>();
        String[] name = new String[]{"21 jump street", "CastAway", "Forrest Gump", "Shawshank Redamption", "Whiplash","The Tourist","12 Years A Slave"};
        int[] poster_id = new int[]{R.drawable.twentyfirstjumpstreet, R.drawable.castaway, R.drawable.forrestgump, R.drawable.shawshankredemption, R.drawable.whiplash,R.drawable.thetourist,R.drawable.twelveyearsaslave};
        String[] rating = new String[]{"7.2", "7.7", "8.8", "9.3", "8.6","6","8.1"};
        String[] synopsis = new String[]{"When cops Schmidt (Jonah Hill) and Jenko (Channing Tatum) join the secret Jump Street unit, they use their youthful appearances to go under cover as high-school students. They trade in their guns and badges for backpacks, and set out to shut down a dangerous drug ring. But, as time goes on, Schmidt and Jenko discover that high school is nothing like it was just a few years earlier -- and, what's more, they must again confront the teenage terror and anxiety they thought they had left behind.",
                "Obsessively punctual FedEx executive Chuck Noland (Tom Hanks) is en route to an assignment in Malaysia when his plane crashes over the Pacific Ocean during a storm. The sole survivor of the flight, Chuck washes ashore on a deserted island. When his efforts to sail away and contact help fail, Chuck learns how to survive on the island, where he remains for years, accompanied by only his handmade volleyball friend, Wilson. Will Chuck ever return to civilization and reunite with his loved ones?"
                , "Slow-witted Forrest Gump (Tom Hanks) has never thought of himself as disadvantaged, and thanks to his supportive mother (Sally Field), he leads anything but a restricted life. Whether dominating on the gridiron as a college football star, fighting in Vietnam or captaining a shrimp boat, Forrest inspires people with his childlike optimism. But one person Forrest cares about most may be the most difficult to save -- his childhood love, the sweet but troubled Jenny (Robin Wright)."
                , "Andy Dufresne (Tim Robbins) is sentenced to two consecutive life terms in prison for the murders of his wife and her lover and is sentenced to a tough prison. However, only Andy knows he didn't commit the crimes. While there, he forms a friendship with Red (Morgan Freeman), experiences brutality of prison life, adapts, helps the warden, etc., all in 19 years.",
                "A first-year music student (Miles Teller) wins a seat behind the drums in a jazz band led by a teacher (J.K. Simmons) who uses fear and intimidation to push his students to perfection."
        ,"During an impromptu trip to Europe to mend a broken heart, math teacher Frank Tupelo (Johnny Depp) finds himself in an extraordinary situation when an alluring stranger, Elise (Angelina Jolie), places herself in his path. Their seemingly innocent flirtation turns into a dangerous game of cat and mouse while various people, who all think that Frank is Elise's thieving paramour, Alexander Pearce, try to capture the pair.",
        "In the years before the Civil War, Solomon Northup (Chiwetel Ejiofor), a free black man from upstate New York, is kidnapped and sold into slavery in the South. Subjected to the cruelty of one malevolent owner (Michael Fassbender), he also finds unexpected kindness from another, as he struggles continually to survive and maintain some of his dignity. Then in the 12th year of the disheartening ordeal, a chance meeting with an abolitionist from Canada changes Solomon's life forever."
        };

        for (int i = 0; i < name.length; i++) {
            MovieInfo movieInfo = new MovieInfo(name[i], poster_id[i], rating[i], synopsis[i]);
            mf.add(movieInfo);
        }

        return mf;
    }
}

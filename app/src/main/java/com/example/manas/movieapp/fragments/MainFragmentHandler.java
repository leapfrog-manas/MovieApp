package com.example.manas.movieapp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.manas.movieapp.Adapters.HomeFragmentRCVAdapter;
import com.example.manas.movieapp.Info.MovieInfo;
import com.example.manas.movieapp.R;
import com.example.manas.movieapp.interfaces.ToolbarAlphaChanger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manas on 3/23/2015.
 */
public class MainFragmentHandler extends Fragment implements ToolbarAlphaChanger{
    ViewGroup mRoot;
    FragmentManager fragmentManager;
    List<MovieInfo> movieInfoList = new ArrayList<>();
    RecyclerView recyclerView;
    RelativeLayout linearLayout;
    ToolbarAlphaChanger toolbarAlphaChanger;


    public MainFragmentHandler() {

    }

    public MainFragmentHandler(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbarAlphaChanger.changeAlpha(255,"asd");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        toolbarAlphaChanger = (ToolbarAlphaChanger) activity;


    }



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        linearLayout = (RelativeLayout) v.findViewById(R.id.linearlayout);


        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 100, 0, 0);
        recyclerView.setLayoutParams(lp);




        movieInfoList = getMovieinfo();
        return v;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        toolbarAlphaChanger.changeAlpha(255,"MovieApp");
        recyclerView.setAdapter(new HomeFragmentRCVAdapter(getActivity(), fragmentManager, movieInfoList));
        toolbarAlphaChanger.changeAlpha(255,"MovieApp");
    }

    public List<MovieInfo> getMovieinfo() {
        List<MovieInfo> mf = new ArrayList<>();
        String[] name = new String[]{"21 jump street", "CastAway", "Forrest Gump", "Shawshank Redamption", "Whiplash", "The Tourist", "12 Years A Slave"};
        int[] poster_id = new int[]{R.drawable.twentyfirstjumpstreet, R.drawable.castaway, R.drawable.forrestgump, R.drawable.shawshankredemption, R.drawable.whiplash, R.drawable.thetourist, R.drawable.twelveyearsaslave};
        String[] rating = new String[]{"7.2", "7.7", "8.8", "9.3", "8.6", "6", "8.1"};
        String[] synopsis = new String[]{"When cops Schmidt (Jonah Hill) and Jenko (Channing Tatum) join the secret Jump Street unit, they use their youthful appearances to go under cover as high-school students. They trade in their guns and badges for backpacks, and set out to shut down a dangerous drug ring. But, as time goes on, Schmidt and Jenko discover that high school is nothing like it was just a few years earlier -- and, what's more, they must again confront the teenage terror and anxiety they thought they had left behind.",
                "In 1995, Chuck Noland (Tom Hanks) is a time-obsessed systems engineer, who travels worldwide resolving productivity problems at FedEx depots. He is in a long-term relationship with Kelly Frears (Helen Hunt), with whom he lives in Memphis, Tennessee. Although the couple wants to get married, Chuck's busy schedule interferes with their relationship. A Christmas with relatives is interrupted when Chuck is summoned to resolve a problem in Malaysia. While flying through a violent storm, his airplane crashes into the Pacific Ocean. Chuck is able to escape the sinking plane and is saved by an inflatable life-raft but in the process, loses the raft's emergency locator transmitter. He clings to the life-raft, loses consciousness, and floats all night before being washed up on an island. After he awakens, he explores the island and soon discovers that it is uninhabited.\n" +
                        "\n" +
                        "Several FedEx packages from the crashed plane wash up on the shore, as well as the corpse of one of the pilots (which he buries). He initially tries to signal for rescue and makes an escape attempt with the remnants of his life-raft, but he cannot pass the powerful surf and the coral reefs surrounding the island. He searches for food, water, shelter, and opens the packages, finding a number of potentially useful items. He leaves one package, with a pair of wings painted on it, unopened. During a first attempt to make fire, Chuck receives a deep wound to his hand. In anger and pain, he throws several objects, including a Wilson volleyball from one of the packages. A short time later he draws a face in the bloody hand print on the ball, names it Wilson and begins talking to it.\n" +
                        "\n" +
                        "Four years later, Chuck is dramatically thinner, bearded, his hair is longer, and he is wearing a loincloth. He has become adept at spearing fish and making fires. He also has regular conversations and arguments with Wilson, his volleyball friend. A large section from a portable toilet washes up on the island; Chuck uses it as a sail in the construction of a raft. After spending some time building and stocking the raft and deciding when the weather conditions will be optimal (using an analemma he has created in his cave to monitor the time of year), he launches, using the sail to overcome the powerful surf. After some time on the ocean, a storm nearly tears his raft apart. The following day, Wilson falls from the raft and is cast away into the ocean, leaving Chuck overwhelmed by loneliness. Later, he is found drifting by a passing cargo ship.\n" +
                        "\n" +
                        "Upon returning to civilization, Chuck learns that he has long been given up for dead; his family and acquaintances have held a funeral, and Kelly has since married Chuck's dentist and has a daughter. After reuniting with Kelly, the pair profess their love for each other but, realizing a future together would be impossible because of her commitment to her family, they part. Kelly gives Chuck the keys to the car they once shared. After buying a new volleyball, Chuck travels to Canadian, Texas to return the unopened FedEx package to its sender. The house at the address is empty, so he leaves the package at the door with a note saying that the package saved his life. He departs and stops at a remote crossroads. A woman passing by in a pickup truck stops to explain where each road leads. As she drives away, Chuck notices the illustration on her truck is identical to the one on the parcel. Chuck is left looking down each road and then towards the departing woman in the truck, smiling as the wind blows in her direction.Obsessively punctual FedEx executive Chuck Noland (Tom Hanks) is en route to an assignment in Malaysia when his plane crashes over the Pacific Ocean during a storm. The sole survivor of the flight, Chuck washes ashore on a deserted island. When his efforts to sail away and contact help fail, Chuck learns how to survive on the island, where he remains for years, accompanied by only his handmade volleyball friend, Wilson. Will Chuck ever return to civilization and reunite with his loved ones?"
                , "Slow-witted Forrest Gump (Tom Hanks) has never thought of himself as disadvantaged, and thanks to his supportive mother (Sally Field), he leads anything but a restricted life. Whether dominating on the gridiron as a college football star, fighting in Vietnam or captaining a shrimp boat, Forrest inspires people with his childlike optimism. But one person Forrest cares about most may be the most difficult to save -- his childhood love, the sweet but troubled Jenny (Robin Wright)."
                , "Andy Dufresne (Tim Robbins) is sentenced to two consecutive life terms in prison for the murders of his wife and her lover and is sentenced to a tough prison. However, only Andy knows he didn't commit the crimes. While there, he forms a friendship with Red (Morgan Freeman), experiences brutality of prison life, adapts, helps the warden, etc., all in 19 years.",
                "A first-year music student (Miles Teller) wins a seat behind the drums in a jazz band led by a teacher (J.K. Simmons) who uses fear and intimidation to push his students to perfection."
                , "During an impromptu trip to Europe to mend a broken heart, math teacher Frank Tupelo (Johnny Depp) finds himself in an extraordinary situation when an alluring stranger, Elise (Angelina Jolie), places herself in his path. Their seemingly innocent flirtation turns into a dangerous game of cat and mouse while various people, who all think that Frank is Elise's thieving paramour, Alexander Pearce, try to capture the pair.",
                "In the years before the Civil War, Solomon Northup (Chiwetel Ejiofor), a free black man from upstate New York, is kidnapped and sold into slavery in the South. Subjected to the cruelty of one malevolent owner (Michael Fassbender), he also finds unexpected kindness from another, as he struggles continually to survive and maintain some of his dignity. Then in the 12th year of the disheartening ordeal, a chance meeting with an abolitionist from Canada changes Solomon's life forever."
        };

        for (int i = 0; i < name.length; i++) {
            MovieInfo movieInfo = new MovieInfo(name[i], poster_id[i], rating[i], synopsis[i]);
            mf.add(movieInfo);
        }

        return mf;
    }


    @Override
    public void changeAlpha(int alpha, String title) {

    }

    @Override
    public void defaultAlpha() {

    }

}

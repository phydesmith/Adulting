package com.example.adulting.jdata.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.adulting.jdata.dao.CardDAO;
import com.example.adulting.jdata.dao.CardInfoDAO;
import com.example.adulting.jdata.dao.CardTypeDAO;
import com.example.adulting.jdata.dao.ResponseDAO;
import com.example.adulting.jdata.entity.CardInfo;
import com.example.adulting.jdata.entity.CardType;
import com.example.adulting.jdata.entity.Response;

@Database(entities = {CardType.class, CardInfo.class, Response.class}, version = 2 )
public abstract class CardDatabase extends RoomDatabase {

    private static CardDatabase instance;

    public abstract CardTypeDAO cardTypeDAO();
    public abstract CardInfoDAO cardInfoDAO();
    public abstract ResponseDAO responseDAO();
    public abstract CardDAO cardDAO();

    public static synchronized CardDatabase getInstance(Context context) {
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    CardDatabase.class, "card_database")
                    .addCallback(roomCallback)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return  instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private CardTypeDAO cardTypeDAO;
        private CardInfoDAO cardInfoDAO;
        private ResponseDAO responseDAO;

        private PopulateDbAsyncTask(CardDatabase db) {
            cardTypeDAO = db.cardTypeDAO();
            cardInfoDAO = db.cardInfoDAO();
            responseDAO = db.responseDAO();
        }

        @Override
        protected  Void doInBackground(Void... voids) {
            // On create here

            cardTypeDAO.insert(new CardType("Relationship"));
            cardTypeDAO.insert(new CardType("Education"));
            cardTypeDAO.insert(new CardType("Health"));
            cardTypeDAO.insert(new CardType("Wealth"));

            cardInfoDAO.insert(new CardInfo(1, "Dinner with Co-workers", "Your co-workers invited you to grab some dinner with them after closing."));
            cardInfoDAO.insert(new CardInfo(1, "Valentines Day", "You and your spouse decided to not buy each other gifts."));
            cardInfoDAO.insert(new CardInfo(1, "Honeymoon ", "You and your spouse have been recently married. You take a week to go to Cuba."));
            cardInfoDAO.insert(new CardInfo(1, "Drinks After Work ", "After a hard day of work your friends ask you to drink and play pool at the bar "));
            cardInfoDAO.insert(new CardInfo(1, "Friends or Girlfriend", "Both your girlfriend and Friends ask you to hang out on a friday night. "));
            cardInfoDAO.insert(new CardInfo(1, "Guys Weekend", "Your friends invite you to go to Door Country for the weekend. "));
            cardInfoDAO.insert(new CardInfo(2, "Community workshop", "You signed up to go to a class sponsored by a local community organization at 8:00am"));
            cardInfoDAO.insert(new CardInfo(2, "AP Classes", "You are debating on whether or not you should take a difficult AP class during high school."));
            cardInfoDAO.insert(new CardInfo(2, "Final exam", "Your grade in economics is sustainable to skip the exam, but it will leave you with a low C, hurting your GPA"));
            cardInfoDAO.insert(new CardInfo(2, "Night class", "You have a night class but you are feeling extremely weak and sick. "));
            cardInfoDAO.insert(new CardInfo(2, "Which class?", "You can sign up for a class you know will easy and you won't gain much knowledge from it, or you can also sign up for a harder class where you will end up learning more. "));
            cardInfoDAO.insert(new CardInfo(2, "Choosing a college", "You can go to a smaller private school for more money and a personalized education. Or go to a bigger public univeristy where you are unsure whether you will succeed in a large lecture hall, but you also save money. "));
            cardInfoDAO.insert(new CardInfo(3, "Hernia", "You notice a bulge in your abdomen after doing some lifting around the house. "));
            cardInfoDAO.insert(new CardInfo(3, "Dentist visit ", "You have major tooth pain and should see the dentist, but you are unsure if you can afford, it if it’s even worth going. "));
            cardInfoDAO.insert(new CardInfo(3, "Daily workout ", "Your best friend expects you to meet at the gym for a daily workout, although you are feeling slightly under the weather."));
            cardInfoDAO.insert(new CardInfo(3, "Where to Eat", "You can save money and eat at McDonalds or spend a litte bit more and eat a Chick Fila salad and eat healthier"));
            cardInfoDAO.insert(new CardInfo(3, "What Snack?", "You want a snack and your options are cookies and berries"));
            cardInfoDAO.insert(new CardInfo(4, "Phish", "Nigerian Prince offers you inheritance to $100,000,000 estate if you send him a processing fee of $100 via Western Mutual."));
            cardInfoDAO.insert(new CardInfo(4, "Play the lottery ", "You have a strong hunch that this week you know the winning numbers."));
            cardInfoDAO.insert(new CardInfo(4, "Invest into a penny stock ", "Your brothers friends uncles cousin has a startup company for a new fuel source, he invites you to become one of the first investors. "));
            cardInfoDAO.insert(new CardInfo(4, "Which Jacket", "You need a new winter coat, you can be cold and buy a cheap or buy a warmer more expensive one. if you go with the cheaper one you're more likely of getting sick through out the winter."));
            cardInfoDAO.insert(new CardInfo(4, "Gym Membership", "You want to get more fit but then you'll need to buy a gym membership which comes at a monthly fee"));

            responseDAO.insert(new Response(1,"Say yes, you're hungry",false,0,0,0,0,10,0,0,-10));
            responseDAO.insert(new Response(1,"Say no, go home and make something",false,0,0,0,0,-5,0,0,5));
            responseDAO.insert(new Response(1,"Say yes, but be frugal",true,0,0,0,40,10,0,0,-5));
            responseDAO.insert(new Response(2,"Don't buy gifts and spend the night watching netflix",false,0,0,0,0,0,0,0,10));
            responseDAO.insert(new Response(2,"Buy a gift and take spouse out to dinner anyways.",false,0,0,0,0,10,0,0,-5));
            responseDAO.insert(new Response(2,"Go bananas and book a weekend at the Sybaris, robes with initials, chocolate strawberries and a honeymoon suite put your spouse eternally into your debt.",true,20,0,0,40,25,0,0,-20));
            responseDAO.insert(new Response(3,"Follow through with your plans and have the week of your life ",false,0,0,0,0,15,0,5,-5));
            responseDAO.insert(new Response(3,"Reschedule honeymoon becuase you are absolutely flooded at work",false,0,0,0,0,-10,0,0,5));
            responseDAO.insert(new Response(3,"as an extra suprise you tell your wife you are actually staying for 2 weeks",true,30,0,0,0,15,0,10,-5));
            responseDAO.insert(new Response(4,"Get drinks after work, create and strengthen new friendships",false,0,0,0,0,10,0,0,0));
            responseDAO.insert(new Response(4,"Stay at work and get ahead. ",false,0,0,0,0,-5,5,0,0));
            responseDAO.insert(new Response(4,"you get so drunk and become best friends with the guys, along with buying shots for everyone all night long",true,35,0,0,0,20,0,-5,-10));
            responseDAO.insert(new Response(5,"Hang out with your girlfriend ",false,0,0,0,0,0,0,0,0));
            responseDAO.insert(new Response(5,"Hang out with your friends ",false,0,0,0,0,0,0,0,0));
            responseDAO.insert(new Response(5,"Hang out as a big group",true,25,0,0,0,0,0,0,0));
            responseDAO.insert(new Response(6,"Go and have a good time, but you end up spending money. ",false,0,0,0,0,10,0,0,-10));
            responseDAO.insert(new Response(6,"Don't go and save money but miss out on a fun time. ",false,0,0,0,0,-10,0,0,10));
            responseDAO.insert(new Response(6,"Go with, but really focus on budgeting yourself",true,0,0,0,30,10,0,0,-5));
            responseDAO.insert(new Response(7,"Sleep in and Skip",false,0,0,0,0,0,-10,5,0));
            responseDAO.insert(new Response(7,"Go to class",false,0,0,0,0,0,10,-5,0));
            responseDAO.insert(new Response(7,"Skip but send an email requesting the notes",true,40,0,0,0,0,-2,-10,0));
            responseDAO.insert(new Response(8,"Sign up for creative writing instead.",false,0,0,0,0,0,5,5,0));
            responseDAO.insert(new Response(8,"Sign up for a single AP class",false,0,0,0,0,0,10,-5,0));
            responseDAO.insert(new Response(8,"Take 2 AP classes per semester, allowing you to graduate with a competitive GPA, setting you up to attend a prestigious college",true,0,20,0,0,0,20,-10,0));
            responseDAO.insert(new Response(9,"Take the exam like a normal student",false,0,0,0,0,0,5,0,0));
            responseDAO.insert(new Response(9,"skip and travel a few days earlier for spring break",false,0,0,0,0,5,-5,0,0));
            responseDAO.insert(new Response(9,"Take the exam and get 100%, leaving you with an A in the class and putting you on honor roll",true,0,25,0,0,0,20,0,0));
            responseDAO.insert(new Response(10,"Attend night class maintaining 100% attendence",false,0,0,0,0,0,10,0,0));
            responseDAO.insert(new Response(10,"skip class and see your S/O because she will only be free one day this week",false,0,0,0,0,10,-10,0,0));
            responseDAO.insert(new Response(10,"Go out with S/O to a 5 star dinner",true,0,0,0,30,5,-10,0,5));
            responseDAO.insert(new Response(11,"Easy class",false,0,0,0,0,0,-10,5,0));
            responseDAO.insert(new Response(11,"Hard Class",false,0,0,0,0,0,10,-5,0));
            responseDAO.insert(new Response(11,"Find a new class that is the middle ",true,0,15,0,0,0,10,0,5));
            responseDAO.insert(new Response(12,"Private School",false,0,0,0,0,0,10,0,-15));
            responseDAO.insert(new Response(12,"Public School",false,0,0,0,0,0,-15,0,10));
            responseDAO.insert(new Response(12,"Apply for as much scholarships as you can at the private school.",true,0,20,0,0,0,15,0,5));
            responseDAO.insert(new Response(13,"Ignore it.",false,0,0,0,0,0,0,-10,5));
            responseDAO.insert(new Response(13,"Go to doctor immediatly.",false,0,0,0,0,0,0,5,5));
            responseDAO.insert(new Response(13,"Ignore it and take it easy, doing some exercises you found on the internet. ",true,0,0,50,0,0,0,0,5));
            responseDAO.insert(new Response(14,"See the dentist to evaluate your tooth issue",false,0,0,0,0,0,0,-10,0));
            responseDAO.insert(new Response(14,"Skip going to the dentist and risk whatever is happening in your mouth",false,0,0,0,0,0,0,10,-5));
            responseDAO.insert(new Response(14,"Easily pay for the cavity aswell as getting your teeth whitened, making you into a whole new person",true,0,0,0,50,0,0,15,-5));
            responseDAO.insert(new Response(15,"Meet with your friend and work out as you have planned. ",false,0,0,0,0,0,0,10,0));
            responseDAO.insert(new Response(15,"Bail on friend, sit at home and sleep instead",false,0,0,0,0,-10,0,-5,0));
            responseDAO.insert(new Response(15,"Work out with friend and introduce a new routine that is more efficient and effective than the one before. ",true,0,20,0,0,5,5,15,0));
            responseDAO.insert(new Response(16,"McDAnks",false,0,0,0,0,0,0,-10,-5));
            responseDAO.insert(new Response(16,"Chick Fila Salad",false,0,0,0,0,0,0,5,-10));
            responseDAO.insert(new Response(16,"Find the cheapiest thing at Chick Fila",true,0,0,0,10,0,0,5,5));
            responseDAO.insert(new Response(17,"Cookies",false,0,0,0,0,0,0,-5,0));
            responseDAO.insert(new Response(17,"Berries",false,0,0,0,0,0,0,5,0));
            responseDAO.insert(new Response(17,"Berry Cookie",true,0,0,0,10,0,0,0,0));
            responseDAO.insert(new Response(18,"Send $100 via Western union.",false,0,0,0,0,0,1,0,-10));
            responseDAO.insert(new Response(18,"Ignore and report email to email provider.",false,0,0,0,0,0,1,0,10));
            responseDAO.insert(new Response(18,"Counterphish using your h4ck3r skills, you gain acess to the scammer's bank account and drain his ill-gotten gains. Requires low relationship and high education",true,20,50,0,0,0,0,0,20));
            responseDAO.insert(new Response(19,"Save your money and buy a sandwich instead, you're really hungry",false,0,0,0,0,0,0,3,-1));
            responseDAO.insert(new Response(19,"Play your lucky numbers and .....?",false,0,0,0,0,0,0,0,5));
            responseDAO.insert(new Response(19,"you play random numbers and....",true,10,10,10,10,0,0,0,15));
            responseDAO.insert(new Response(20,"You think penny stocks are a scam and waste of time and money! You choose not to invest",false,0,0,0,0,-5,0,0,-2));
            responseDAO.insert(new Response(20,"Since you are very helpful you choose to invest $500 into his penny stock",false,0,0,0,0,3,2,0,7));
            responseDAO.insert(new Response(20,"You ask for more information and he convinces you to invest, you throw him $10,000, and it pays off",true,0,0,0,45,10,0,0,15));
            responseDAO.insert(new Response(21,"Cheap Coat",false,0,0,0,0,0,0,-15,10));
            responseDAO.insert(new Response(21,"Expensive Coat",false,0,0,0,0,0,0,15,-5));
            responseDAO.insert(new Response(21,"Buy a nice used winter coat",true,0,5,0,5,0,0,10,-2));
            responseDAO.insert(new Response(22,"Buy memebership",false,0,0,0,0,0,0,15,-10));
            responseDAO.insert(new Response(22,"Don't buy one",false,0,0,0,0,0,0,-15,10));
            responseDAO.insert(new Response(22,"Use what you have around your house for weights and overtime buy your own weights as a long term investment",true,0,15,0,5,0,0,10,-5));
            return null;
        }

    }

}

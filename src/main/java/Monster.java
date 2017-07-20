import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Monster {
  public String name;
  public int personId;
  public int id;
  public int foodLevel;
  public int sleepLevel;
  public int playLevel;
  public Timestamp birthday;
  public Timestamp lastSlept;
  public Timestamp lastAte;
  public Timestamp lastPlayed;
  public Timer timer;
  public String type;

  public static final int MAX_FOOD_LEVEL = 3;
  public static final int MAX_SLEEP_LEVEL = 8;
  public static final int MAX_PLAY_LEVEL = 12;
  public static final int MIN_ALL_LEVELS = 0;

  public String getType() {
    return type;
  }

  public String getName(){
    return name;
  }

  public int getPersonId(){
    return personId;
  }

  public int getId(){
    return id;
  }

  public int getPlayLevel(){
    return playLevel;
  }

  public int getSleepLevel(){
    return sleepLevel;
  }

  public int getFoodLevel(){
    return foodLevel;
  }

  public Timestamp getBirthday(){
    return birthday;
  }

  public Timestamp getLastSlept(){
    return lastSlept;
  }

  public Timestamp getLastAte(){
   return lastAte;
 }
 public Timestamp getLastPlayed(){
    return lastPlayed;
  }

  @Override
  public boolean equals(Object otherMonster){
    if (!(otherMonster instanceof Monster)) {
      return false;
    } else {
      Monster newMonster = (Monster) otherMonster;
      return this.getName().equals(newMonster.getName()) &&
             this.getPersonId() == newMonster.getPersonId();
    }
  }

  public boolean isAlive() {
    if (foodLevel <= MIN_ALL_LEVELS ||
    playLevel <= MIN_ALL_LEVELS ||
    sleepLevel <= MIN_ALL_LEVELS) {
      return false;
    }
    return true;
  }

  public void depleteLevels(){
  if (isAlive()){
    playLevel--;
    foodLevel--;
    sleepLevel--;
  }
}

  public void play(){
  if (playLevel >= MAX_PLAY_LEVEL){
    throw new UnsupportedOperationException("You cannot play with monster anymore!");
  }
  try(Connection con = DB.sql2o.open()) {
    String sql = "UPDATE monsters SET lastplayed = now() WHERE id = :id";
    con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    playLevel++;
    String sqlfeed = "UPDATE monsters SET playLevel = " + playLevel +  " WHERE id = :id";
    con.createQuery(sqlfeed)
      .addParameter("id", id)
      .executeUpdate();
  }
}

  public void sleep(){
    if (sleepLevel >= MAX_SLEEP_LEVEL){
      throw new UnsupportedOperationException("You cannot make your monster sleep anymore!");
    }
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE monsters SET lastslept = now() WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
      sleepLevel++;
      String sqlfeed = "UPDATE monsters SET sleepLevel = " + sleepLevel +  " WHERE id = :id";
      con.createQuery(sqlfeed)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void feed(){
  if (foodLevel >= MAX_FOOD_LEVEL){
    throw new UnsupportedOperationException("You cannot feed your monster anymore!");
  }
  try(Connection con = DB.sql2o.open()) {
    String sql = "UPDATE monsters SET lastate = now() WHERE id = :id";
    con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    foodLevel++;
    String sqlfeed = "UPDATE monsters SET foodLevel = " + foodLevel +  " WHERE id = :id";
    con.createQuery(sqlfeed)
      .addParameter("id", id)
      .executeUpdate();
  }
}

public void startTimer(){
    Monster currentMonster = this;
    TimerTask timerTask = new TimerTask(){
      @Override
      public void run() {
        if (currentMonster.isAlive() == false){
          cancel();
        }
        depleteLevels();
      }
    };
    this.timer.schedule(timerTask, 0, 600);
  }


}

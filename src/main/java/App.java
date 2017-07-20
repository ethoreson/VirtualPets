import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("users", Person.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/user-new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/new-user-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/user/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String email = request.queryParams("email");
      try {
        Person user = new Person(name, email);
        user.save();
        model.put("user", user);
        String url = String.format("/user/%d", user.getId());
        response.redirect(url);
      } catch (IllegalArgumentException exception) {
        response.redirect("/bademail");
      }
      model.put("template", "templates/user-details.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/user/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Person user = Person.find(Integer.parseInt(request.params(":id")));
      model.put("user", user);
      model.put("monsters", user.getMonsters());
      model.put("template", "templates/user-details.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/user/:userId/monster/:monsterId", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Person user = Person.find(Integer.parseInt(request.params(":userId")));
      String monsterName = request.queryParams("monster-name");
      String monsterType = request.queryParams("monster-type");
      if (monsterType.equals("fire")) {
        FireMonster monster = new FireMonster(monsterName, user.getId());
        monster.save();
        model.put("monster", monster);
        model.put("user", user);
        String url = String.format("/user/%d/monster/%d", user.getId(), monster.getId());
        response.redirect(url);
      } else {
        WaterMonster monster = new WaterMonster(monsterName, user.getId());
        monster.save();
        model.put("monster", monster);
        model.put("user", user);
        String url = String.format("/user/%d/monster/%d", user.getId(), monster.getId());
        response.redirect(url);
      }
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/user/:userId/monster/:monsterId", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Person user = Person.find(Integer.parseInt(request.params(":userId")));
      if ((FireMonster.find(Integer.parseInt(request.params(":monsterId")))).getType().equals("fire")) {
        FireMonster monster = FireMonster.find(Integer.parseInt(request.params(":monsterId")));
        model.put("monster", monster);
      } else {
        WaterMonster monster = WaterMonster.find(Integer.parseInt(request.params(":monsterId")));
        model.put("monster", monster);
      }
      model.put("user", user);
      model.put("template", "templates/monster-details.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/user/:userId/monster/:monsterId/:type/feed", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Person user = Person.find(Integer.parseInt(request.params(":userId")));
      model.put("user", user);
      String type = request.params(":type");
      if (type.equals("fire")) {
        FireMonster monster = FireMonster.find(Integer.parseInt(request.params(":monsterId")));
        try {
          monster.feed();
        } catch (UnsupportedOperationException exception) {
          response.redirect("/oops");
        }
        model.put("monster", monster);
        String url = String.format("/user/%d/monster/%d", user.getId(), monster.getId());
        response.redirect(url);
      } else {
        WaterMonster monster = WaterMonster.find(Integer.parseInt(request.params(":monsterId")));
        try {
          monster.feed();
        } catch (UnsupportedOperationException exception) {
          response.redirect("/oops");
        }
        model.put("monster", monster);
        String url = String.format("/user/%d/monster/%d", user.getId(), monster.getId());
        response.redirect(url);
      }
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/user/:userId/monster/:monsterId/:type/sleep", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Person user = Person.find(Integer.parseInt(request.params(":userId")));
      model.put("user", user);
      String type = request.params(":type");
      if (type.equals("fire")) {
        FireMonster monster = FireMonster.find(Integer.parseInt(request.params(":monsterId")));
        try {
          monster.sleep();
        } catch (UnsupportedOperationException exception) {
          response.redirect("/oops");
        }
        model.put("monster", monster);
        String url = String.format("/user/%d/monster/%d", user.getId(), monster.getId());
        response.redirect(url);
      } else {
        WaterMonster monster = WaterMonster.find(Integer.parseInt(request.params(":monsterId")));
        try {
          monster.sleep();
        } catch (UnsupportedOperationException exception) {
          response.redirect("/oops");
        }
        model.put("monster", monster);
        String url = String.format("/user/%d/monster/%d", user.getId(), monster.getId());
        response.redirect(url);
      }
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/user/:userId/monster/:monsterId/:type/play", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Person user = Person.find(Integer.parseInt(request.params(":userId")));
      model.put("user", user);
      String type = request.params(":type");
      if (type.equals("fire")) {
        FireMonster monster = FireMonster.find(Integer.parseInt(request.params(":monsterId")));
        try {
          monster.play();
        } catch (UnsupportedOperationException exception) {
          response.redirect("/oops");
        }
        model.put("monster", monster);
        String url = String.format("/user/%d/monster/%d", user.getId(), monster.getId());
        response.redirect(url);
      } else {
        WaterMonster monster = WaterMonster.find(Integer.parseInt(request.params(":monsterId")));
        try {
          monster.play();
        } catch (UnsupportedOperationException exception) {
          response.redirect("/oops");
        }
        model.put("monster", monster);
        String url = String.format("/user/%d/monster/%d", user.getId(), monster.getId());
        response.redirect(url);
      }
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/oops", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/oops.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/bademail", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/bademail.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // get("/user/:id/monsters/new", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   model.put("template", "templates/stylist-form.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());

  }
}

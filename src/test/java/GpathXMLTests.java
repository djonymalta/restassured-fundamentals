import Config.VideoGameConfig;
import Config.VideoGameEndPoints;
import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.element.Node;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class GpathXMLTests extends VideoGameConfig {

    @Test
    public void getFirstGameInList(){

        Response response =
                get(VideoGameEndPoints.ALL_VIDEO_GAMES);

        String name = response.path("List.item.name[0]");

        System.out.println(name);
    }

    @Test
    public void getAttribute(){
        Response response = get(VideoGameEndPoints.ALL_VIDEO_GAMES);

                String categody = response.path("List.item[0].@category");
        System.out.println(categody);
    }

    @Test
    public void getListOfXmlNodes(){
        String responseAsString = get(VideoGameEndPoints.ALL_VIDEO_GAMES).asString();

        List<Node> allResults = XmlPath.from(responseAsString).get(
                "List.item.findAll { element -> return element } "
        );
        System.out.println(allResults.get(2).get("name").toString());
    }

    @Test
    public void getListOfXmlNodesByFindAllAttribute() {
        String responseAsString = get(VideoGameEndPoints.ALL_VIDEO_GAMES).asString();

        List<Node> allDrivingGames = XmlPath.from(responseAsString).get("List.item.findAll { game -> def category = game.@category; category == 'Driving'}");
        System.out.println(allDrivingGames.get(0).get("name").toString());

    };

    @Test
    public void getSingleNode(){
        String responseAsString = get(VideoGameEndPoints.ALL_VIDEO_GAMES).asString();

        Node videoGame = XmlPath.from(responseAsString).get("List.item.find { game -> def name = game.name; name == 'Tetris' }");

        String videoGameName = videoGame.get("name").toString();
        System.out.println(videoGameName);
    }


    @Test
    public void getSingleElementDepthFirstSearch(){
        String responseAsString = get(VideoGameEndPoints.ALL_VIDEO_GAMES).asString();

        int reviewScore = XmlPath.from(responseAsString).getInt(
                "**.find { it.name == 'Gran Turismo 3' }.reviewScore"
        );
        System.out.println(reviewScore);
    }
}

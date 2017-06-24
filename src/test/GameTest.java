import com.TicTacToe.Game;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GameTest {

    Game game;

    @Before
    public void initialize() {
        game = new Game();
    }

    @Test
    public void shouldReturnXWhenRowXWinConditionsMatched() {
        for (int i = 1; i <= 3; i++) {
            game.setField(i, "X");
        }
        assertThat(game.checkForWinConditions()).isEqualTo("X");
    }

    @Test
    public void shouldReturnOWhenRowOWinConditionMatched(){
        for (int i = 4; i <= 6; i++) {
            game.setField(i, "O");
        }
        assertThat(game.checkForWinConditions()).isEqualTo("O");
    }

    @Test
    public void shouldReturnXWhenColumnXWinConditionMatched(){
        game.setField(1,"X");
        game.setField(4,"X");
        game.setField(7,"X");
        assertThat(game.checkForWinConditions()).isEqualTo("X");
    }

    @Test
    public void shouldReturnOWhenColumnOWinConditionMatched(){
        game.setField(2,"O");
        game.setField(5,"O");
        game.setField(8,"O");
        assertThat(game.checkForWinConditions()).isEqualTo("O");
    }

    @Test
    public void shouldReturnXWhenCrossXConditionsMatched(){
        game.setField(1,"X");
        game.setField(5,"X");
        game.setField(9,"X");
        assertThat(game.checkForWinConditions()).isEqualTo("X");
    }

    @Test
    public void shouldReturnOWhenCrossOConditionsMatched(){
        game.setField(3,"O");
        game.setField(5,"O");
        game.setField(7,"O");
        assertThat(game.checkForWinConditions()).isEqualTo("O");
    }

    @Test
    public void shouldReturnEmptyStringWhenWinConditionsNotMatched(){
        assertThat(game.checkForWinConditions()).isEqualTo("");
    }


}

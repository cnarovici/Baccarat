import java.util.ArrayList;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;


public class BaccaratGame extends Application {
	private ArrayList<Card> playerHand;
	private ArrayList<Card> bankerHand;
	private BaccaratDealer theDealer;
	private BaccaratGameLogic gameLogic;
	private double currentBet;
	private double totalWinnings;
	private double money;
	private String betSelection;
	private String winnerString;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	//Constructor for testing
	public BaccaratGame() {
        playerHand = new ArrayList<>();
        bankerHand = new ArrayList<>();
        theDealer = new BaccaratDealer();
        gameLogic = new BaccaratGameLogic();
        currentBet = 0;
        totalWinnings = 0;
    }
	
	// For testing
    public void setPlayerHand(ArrayList<Card> playerHand) {
        this.playerHand = playerHand;
    }

    public void setBankerHand(ArrayList<Card> bankerHand) {
        this.bankerHand = bankerHand;
    }

    public void setCurrentBet(double currentBet) {
        this.currentBet = currentBet;
    }
    
    public void setBetSelection(String betSelection) {
    	this.betSelection = betSelection;
    }
	
	//Evaluates the winnings based on who won and sets up the string for whoever won and lost.
	public double evaluateWinnings() {
		String winner = gameLogic.whoWon(playerHand, bankerHand);
		if (winner.equals("Player") && winner.equals(betSelection)) {
			winnerString = "Congrats, you bet Player! You win!";
			currentBet *= 2;
		} else if (winner.equals("Banker") && winner.equals(betSelection)) {
			winnerString = "Congrats, you bet Banker! You win!";
			currentBet = (currentBet*.95) + currentBet;
		} else if (winner.equals("Draw") && winner.equals(betSelection)) {
			winnerString = "Congrats, you bet Draw! You win!";
			currentBet *= 9;
		} else {
			winnerString = "Sorry, you bet " + betSelection + "! You lost your bet!";
			currentBet = 0;
		}
		return currentBet;
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Welcome to Baccarat");
		
		winnerString = null;
		theDealer = new BaccaratDealer();
		gameLogic = new BaccaratGameLogic();
		theDealer.generateDeck();
		playerHand = new ArrayList<Card>();
		bankerHand = new ArrayList<Card>();
		money = 1000;
		betSelection = null;
		
		Text postGameStats = new Text("Player Total: " + gameLogic.handTotal(playerHand) +
				"\tBanker Total: " + gameLogic.handTotal(bankerHand) +
				"\n" + gameLogic.whoWon(playerHand, bankerHand) + " wins" +
				"\n" + winnerString);
		Text currentMoney = new Text("Your money: $" + money + "\n\n");
		currentMoney.setFont(Font.font(25));
		Text winnings = new Text("Winnings: $" + totalWinnings);
		winnings.setFont(Font.font(25));
		Text betAmount = new Text("Current Bet: $" + currentBet);
		betAmount.setFont(Font.font(16));
		
		Button deal = new Button("Deal");
		deal.setMinSize(100, 50);
		deal.setDisable(true);
		
		VBox playBox = new VBox(deal);
		playBox.setPrefSize(300, 300);
		playBox.setStyle("-fx-border-color: black;" +
				"-fx-border-width: 2;" +
				"-fx-border-insets: 5;" +
				"-fx-border-radius: 5;");
		
		//Button to reset your selected bets
		Button resetBet = new Button("Reset");
		resetBet.setDisable(true);
		resetBet.setOnAction(event -> {
			currentBet = 0;
			betSelection = null;
			betAmount.setText("Current Bet: $" + currentBet);
			resetBet.setDisable(true);
			deal.setDisable(true);
		});
		
		VBox moneyBox = new VBox(currentMoney, winnings);
		moneyBox.setStyle("-fx-border-color: black;" +
				"-fx-border-width: 2;" +
				"-fx-border-insets: 5;" +
				"-fx-border-radius: 5;");
		
		HBox bottomBettingCorner = new HBox(betAmount, resetBet);
		
		//Code to set up the money betting buttons
		Button betTen = new Button("$10");
		betTen.setStyle("-fx-background-radius: 60px;" +
				"-fx-min-width: 60px;" +
				"-fx-min-height: 60px;" +
				"-fx-max-width: 60px;" +
				"-fx-max-height: 60px;");
		betTen.setOnAction(event -> {
			if (money >= 10 && currentBet+10 <= money) {
				currentBet += 10;
				betAmount.setText("Current Bet: $" + currentBet);
				resetBet.setDisable(false);
				if (betSelection != null) {
					deal.setDisable(false);
				}
			}
		});
		Button betFifty = new Button("$50");
		betFifty.setStyle("-fx-background-radius: 60px;" +
				"-fx-min-width: 60px;" +
				"-fx-min-height: 60px;" +
				"-fx-max-width: 60px;" +
				"-fx-max-height: 60px;");
		betFifty.setOnAction(event -> {
			if (money >= 50 && currentBet+50 <= money) {
				currentBet += 50;
				betAmount.setText("Current Bet: $" + currentBet);
				resetBet.setDisable(false);
				if (betSelection != null) {
					deal.setDisable(false);
				}
			}
		});
		Button betHundred = new Button("$100");
		betHundred.setStyle("-fx-background-radius: 60px;" +
				"-fx-min-width: 60px;" +
				"-fx-min-height: 60px;" +
				"-fx-max-width: 60px;" +
				"-fx-max-height: 60px;");
		betHundred.setOnAction(event -> {
			if (money >= 100 && currentBet+100 <= money) {
				currentBet += 100;
				betAmount.setText("Current Bet: $" + currentBet);
				resetBet.setDisable(false);
				if (betSelection != null) {
					deal.setDisable(false);
				}
			}
		});
		
		//Custom bet confirm button and text field is set up
		TextField customBet = new TextField();
		customBet.setPromptText("Enter custom bet");

		Button confirmBet = new Button("Confirm");
		confirmBet.setOnAction(event -> { //Parses String input to see if there is a double to add to what is being bet
			double temp = Double.parseDouble(customBet.getText());
			if (temp <= money && currentBet + temp <= money) {
				customBet.clear();
				currentBet += temp;
				currentBet = (double)Math.round(currentBet*100)/100;
				betAmount.setText("Current Bet: $" + currentBet);
				resetBet.setDisable(false);
				if (betSelection != null) {
					deal.setDisable(false);
				}
			}
		});
		
		HBox customBetContainer = new HBox(customBet, confirmBet);
		customBetContainer.setAlignment(Pos.CENTER);
		customBetContainer.setPadding(new Insets(20, 0, 0, 0));
		
		//Player button style and functionality
		Button betPlayer = new Button("Player");
		betPlayer.setStyle("-fx-background-radius: 60px;" +
				"-fx-min-width: 60px;" +
				"-fx-min-height: 60px;" +
				"-fx-max-width: 60px;" +
				"-fx-max-height: 60px;");
		betPlayer.setOnAction(event -> {
			betSelection = "Player";
			if (currentBet != 0) {
				deal.setDisable(false);
			}
		});
		
		//Banker button style and functionality
		Button betBanker = new Button("Banker");
		betBanker.setStyle("-fx-background-radius: 60px;" +
				"-fx-min-width: 60px;" +
				"-fx-min-height: 60px;" +
				"-fx-max-width: 60px;" +
				"-fx-max-height: 60px;");
		betBanker.setOnAction(event -> {
			betSelection = "Banker";
			if (currentBet != 0) {
				deal.setDisable(false);
			}
		});
		
		//Tie button style and functionality
		Button betTie = new Button("Tie");
		betTie.setStyle("-fx-background-radius: 60px;" +
				"-fx-min-width: 60px;" +
				"-fx-min-height: 60px;" +
				"-fx-max-width: 60px;" +
				"-fx-max-height: 60px;");
		betTie.setOnAction(event -> {
			betSelection = "Draw";
			if (currentBet != 0) {
				deal.setDisable(false);
			}
		});
		
		HBox betChoice = new HBox(betPlayer, betBanker, betTie);
		HBox betAmmount = new HBox(betTen, betFifty, betHundred);
		
		VBox bettingCorner = new VBox(betChoice, betAmmount, customBetContainer, bottomBettingCorner);
		bettingCorner.setStyle("-fx-border-color: black;" +
				"-fx-border-width: 2;" +
				"-fx-border-insets: 5;" +
				"-fx-border-radius: 5;");
		
		HBox bottom = new HBox(playBox, moneyBox, bettingCorner);
		
		Text playerSide = new Text("PLAYER");
		playerSide.setFont(Font.font(25));
		Text bankerSide = new Text("BANKER");
		bankerSide.setFont(Font.font(25));
		
		//Default card images are set
		ImageView playerCard1 = new ImageView(new Image("file:src/main/resources/Cards/card_back.jpg"));
		ImageView playerCard2 = new ImageView(new Image("file:src/main/resources/Cards/card_back.jpg"));
		ImageView playerCard3 = new ImageView(new Image("file:src/main/resources/Cards/card_back.jpg"));
		ImageView bankerCard1 = new ImageView(new Image("file:src/main/resources/Cards/card_back.jpg"));
		ImageView bankerCard2 = new ImageView(new Image("file:src/main/resources/Cards/card_back.jpg"));
		ImageView bankerCard3 = new ImageView(new Image("file:src/main/resources/Cards/card_back.jpg"));
		
		//Card image sizing
		playerCard1.setFitWidth(140);
		playerCard1.setFitHeight(190);
		playerCard2.setFitWidth(140);
		playerCard2.setFitHeight(190);
		playerCard3.setFitWidth(140);
		playerCard3.setFitHeight(190);
		bankerCard1.setFitWidth(140);
		bankerCard1.setFitHeight(190);
		bankerCard2.setFitWidth(140);
		bankerCard2.setFitHeight(190);
		bankerCard3.setFitWidth(140);
		bankerCard3.setFitHeight(190);
		
		//Display for the center with the cards and post round info
		HBox playerCardDisplay = new HBox(playerCard1, playerCard2, playerCard3);
		HBox bankerCardDisplay = new HBox(bankerCard1, bankerCard2, bankerCard3);
		VBox playerContent = new VBox(playerSide, playerCardDisplay);
		VBox bankerContent = new VBox(bankerSide, bankerCardDisplay);
		HBox gameDisplay = new HBox(playerContent, postGameStats, bankerContent);
		
		//Deal button functionality
		deal.setOnAction(event -> {
			money -= currentBet;
			totalWinnings -= currentBet;
			boolean temp = false;
			boolean firstHandNaturalWin = false;
			boolean naturalWin = false;
			theDealer.shuffleDeck();
			playerHand = theDealer.dealHand();
			bankerHand = theDealer.dealHand();
			
			//Checks for natural win in both hands after the first draw
			if (gameLogic.handTotal(playerHand) == 8 && gameLogic.handTotal(bankerHand) == 8) {
				firstHandNaturalWin = true;
				currentBet = evaluateWinnings();
			}
			
			
			if (gameLogic.handTotal(playerHand) == 9 && gameLogic.handTotal(bankerHand) == 9) {
				firstHandNaturalWin = true;
				currentBet = evaluateWinnings();
			}
			
			//Checks for natural win in either hands after first draw
			if (gameLogic.handTotal(playerHand) == 8 || gameLogic.handTotal(bankerHand) == 8) {
				naturalWin = true;
			}
			
			if (gameLogic.handTotal(playerHand) == 9 || gameLogic.handTotal(bankerHand) == 9) {
				naturalWin = true;
			}
			
			//Player draws card if evaluatePlayerDraw returns true and if a natural win has not occurred
			if (gameLogic.evaluatePlayerDraw(playerHand) && !naturalWin) {
				playerHand.add(theDealer.drawOne());
			} else {
				playerCard3.setImage(new Image("file:src/main/resources/Cards/card_back.jpg"));
			}
			
			//Updating player card images
			String card1 = playerHand.get(0).getValue() + playerHand.get(0).getSuite();
 			playerCard1.setImage(new Image("file:src/main/resources/Cards/" + card1 + ".png"));
			String card2 = playerHand.get(1).getValue() + playerHand.get(1).getSuite();
 			playerCard2.setImage(new Image("file:src/main/resources/Cards/" + card2 + ".png"));
			if (playerHand.size() == 3) {
				String card3 = playerHand.get(2).getValue() + playerHand.get(2).getSuite();
	 			playerCard3.setImage(new Image("file:src/main/resources/Cards/" + card3 + ".png"));
			}
			
			//Logic for seeing if banker draws a third card
			if (playerHand.size() == 3) {
				temp = gameLogic.evaluateBankerDraw(bankerHand, playerHand.get(2));
			}
			if (playerHand.size() == 2 && !naturalWin) {
				temp = gameLogic.evaluateBankerDraw(bankerHand, null);
			}
			
			if (naturalWin) {
				bankerCard3.setImage(new Image("file:src/main/resources/Cards/card_back.jpg"));
			}
			
			//Banker draws third card if evaluateBankerDraw returned true
			if (temp) {
				bankerHand.add(theDealer.drawOne());
			} else {
				bankerCard3.setImage(new Image("file:src/main/resources/Cards/card_back.jpg"));
			}
			
			//Updating banker card images
			card1 = bankerHand.get(0).getValue() + bankerHand.get(0).getSuite();
			bankerCard1.setImage(new Image("file:src/main/resources/Cards/" + card1 + ".png"));
			card2 = bankerHand.get(1).getValue() + bankerHand.get(1).getSuite();
			bankerCard2.setImage(new Image("file:src/main/resources/Cards/" + card2 + ".png"));
			if (bankerHand.size() == 3) {
				String card3 = bankerHand.get(2).getValue() + bankerHand.get(2).getSuite();
	 			bankerCard3.setImage(new Image("file:src/main/resources/Cards/" + card3 + ".png"));
			}
			
			//Checking if the natural win wasn't a tie on the first draw
			if (!firstHandNaturalWin) {
				currentBet = evaluateWinnings();
			}
			totalWinnings += currentBet;
			money += currentBet;
			
			//Center text which the player sees what happened in the round
			postGameStats.setText("Player Total: " + gameLogic.handTotal(playerHand) +
					"\tBanker Total: " + gameLogic.handTotal(bankerHand) +
					"\n" + gameLogic.whoWon(playerHand, bankerHand) + " wins" +
					"\n" + winnerString);
			
			
			currentBet = 0;
			betSelection = null;
			betAmount.setText("Current Bet: $" + currentBet);
			currentMoney.setText("Your money: $" + money + "\n\n");
			winnings.setText("Winnings: $" + totalWinnings);
			deal.setDisable(true);
			resetBet.setDisable(true);
		});
		
		//Alignment for all graphics
		playBox.setAlignment(Pos.CENTER);
		moneyBox.setAlignment(Pos.CENTER);
		bettingCorner.setAlignment(Pos.CENTER);
		betChoice.setAlignment(Pos.CENTER);
		betAmmount.setAlignment(Pos.CENTER);
		bottomBettingCorner.setAlignment(Pos.CENTER);
		gameDisplay.setAlignment(Pos.CENTER);
		playerContent.setAlignment(Pos.CENTER);
		bankerContent.setAlignment(Pos.CENTER);
		
		//Spacing and padding for all graphics
		betChoice.setSpacing(40);
		betAmmount.setSpacing(40);
		betChoice.setPadding(new Insets(0, 0, 10, 0));
		betAmmount.setPadding(new Insets(10, 0, 0, 0));
		bottomBettingCorner.setSpacing(100);
		bottomBettingCorner.setPadding(new Insets(40, 0, 0, 0));
		gameDisplay.setSpacing(100);
		
		Menu options = new Menu("Options");
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(options);
		
		MenuItem freshStart = new MenuItem("Fresh Start");
		MenuItem exit = new MenuItem("Exit");
		
		options.getItems().addAll(freshStart, exit);
		
		//Fresh start resets everything to as if the program was rerun
		freshStart.setOnAction(event -> {
			playerCard1.setImage(new Image("file:src/main/resources/Cards/card_back.jpg"));
			playerCard2.setImage(new Image("file:src/main/resources/Cards/card_back.jpg"));
			playerCard3.setImage(new Image("file:src/main/resources/Cards/card_back.jpg"));
			bankerCard1.setImage(new Image("file:src/main/resources/Cards/card_back.jpg"));
			bankerCard2.setImage(new Image("file:src/main/resources/Cards/card_back.jpg"));
			bankerCard3.setImage(new Image("file:src/main/resources/Cards/card_back.jpg"));
			money = 1000;
			totalWinnings = 0;
			currentBet = 0;
			betSelection = null;
			currentMoney.setText("Your money: $" + money + "\n\n");
			winnings.setText("Winnings: $" + totalWinnings);
			betAmount.setText("Current Bet: $" + currentBet);
			deal.setDisable(true);
			resetBet.setDisable(true);
		});
		
		exit.setOnAction(event -> {
			Platform.exit();
		});
	     
	     
	     BorderPane root = new BorderPane();
	     root.setStyle("-fx-background-color: MediumTurquoise;");
	     root.setTop(menuBar);
	     root.setCenter(gameDisplay);
	     root.setBottom(bottom);
	     HBox.setHgrow(bettingCorner, Priority.ALWAYS);
	     HBox.setHgrow(moneyBox, Priority.ALWAYS);
	     Scene scene = new Scene(root, 1500,800);
	     
			primaryStage.setScene(scene);
			primaryStage.show();
		
				
		
	}
}

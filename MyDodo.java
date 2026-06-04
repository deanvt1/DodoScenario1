import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *
 * @author Sjaak Smetsers & Renske Smetsers-Weeda
 * @version 3.0 -- 20-01-2017
 */
public class MyDodo extends Dodo
{
    private int myNrOfEggsHatched;

    public MyDodo() {
        super( EAST );
        myNrOfEggsHatched = 0;
    }

    public void act() {

        turn (180);

        turn (1);

    }

    /**
     * Move one cell forward in the current direction.
     * 
     * <P> Initial: Dodo is somewhere in the world
     * <P> Final: If possible, Dodo has moved forward one cell
     *
     */
    public void move() {
        if ( canMove() ) {
            step();
        } else {
            showError( "I'm stuck!" );
        }
    }

    /**
     * Test if Dodo can move forward, (there are no obstructions
     *    or end of world in the cell in front of her).
     * 
     * <p> Initial: Dodo is somewhere in the world
     * <p> Final:   Same as initial situation
     * 
     * @return boolean true if Dodo can move (no obstructions ahead)
     *                 false if Dodo can't move
     *                      (an obstruction or end of world ahead)
     */
    public boolean canMove() {
        if ( borderAhead() ){
            return false;
        } else {
            return true;
        }
    }

    /**
     * Hatches the egg in the current cell by removing
     * the egg from the cell.
     * Gives an error message if there is no egg
     * 
     * <p> Initial: Dodo is somewhere in the world. There is an egg in Dodo's cell.
     * <p> Final: Dodo is in the same cell. The egg has been removed (hatched).     
     */    
    public void hatchEgg () {
        if ( onEgg() ) {
            pickUpEgg();
            myNrOfEggsHatched++;
        } else {
            showError( "There was no egg in this cell" );
        }
    }

    /**
     * Returns the number of eggs Dodo has hatched so far.
     * 
     * @return int number of eggs hatched by Dodo
     */
    public int getNrOfEggsHatched() {
        return myNrOfEggsHatched;
    }

    /**
     * Move given number of cells forward in the current direction.
     * 
     * <p> Initial:   
     * <p> Final:  
     * 
     * @param   int distance: the number of steps made
     */
    public void jump( int distance ) {
        int nrStepsTaken = 0;               // set counter to 0
        while ( nrStepsTaken < distance ) { // check if more steps must be taken  
            move();                         // take a step
            nrStepsTaken++;     // increment the counter
            System.out.println();
        }
    }

    /**
     * Walks to edge of the world printing the coordinates at each step
     * 
     * <p> Initial: Dodo is on West side of world facing East.
     * <p> Final:   Dodo is on East side of world facing East.
     *              Coordinates of each cell printed in the console.
     */

    public void walkToWorldEdgePrintingCoordinates( ){
        while( ! borderAhead() ){

            move(); //loopt totdat hij de border raakt en dan stopt die

        }
    }

    /**
     * Test if Dodo can lay an egg.
     *          (there is not already an egg in the cell)
     * 
     * <p> Initial: Dodo is somewhere in the world
     * <p> Final:   Same as initial situation
     * 
     * @return boolean true if Dodo can lay an egg (no egg there)
     *                 false if Dodo can't lay an egg
     *                      (already an egg in the cell)
     */

    public boolean canLayEgg( ){
        if( onEgg() ){
            return false;
        }else{
            return true;
        }
    }  

    public void climbOverFence()
    {
        {
            turnLeft();
            move();

            turnRight();
            move();
            move();

            turnRight();
            move();

            turnLeft();

        }
    }
    // zolang hij niet op egg is blijft hij lopen als die op egg staat stopt die met lopen
    public void gotoEgg() {
        while (!onEgg()) {
            move();
        }
    }

    public void goBackToStartOfRowAndFaceBack() {
        //draai om loop naar de andere kant draai weer twee keer naar de andere kant.
        turnLeft();
        turnLeft();

        walkToWorldEdgePrintingCoordinates();

        turnLeft();
        turnLeft();

    }

    public void walkToWorldEdgeClimbingOverFences(){
        while( ! borderAhead()     && !onNest()){

            if (fenceAhead()){ 
                climbOverFence();
            } else {
                move();

            }
            if (onNest()){
                layEgg();
            }
        }
    }

    public void pickUpGrainAndPrintCoordinates(){
        while( ! borderAhead()){
            if (onGrain()){ 
                System.out.println(getX() +","+ getY());
                pickUpGrain();  
            }
            move(); //loopt totdat hij de border raakt en dan stopt die
        }
    }

    public void stepOneCellBackwards() {
        // twee keer naar rechts draaien
        turnRight();
        turnRight();
        //draai
        move();
        //weer terug draaien
        turnRight();
        turnRight();
    }

    public void walkToWorldEdgeLayEgg(){
        while( ! borderAhead()){
            move();

            if (onNest()) {
                layEgg();
            }
        }
    }

    public void walkAroundFencedArea() {
        while (!onEgg()){
            turnRight();
            if (fenceAhead()){
                turnLeft();
            }
            move();
        }
    }

    public void walkAroundOtherFencedArea() {
        while (!onEgg()) {
            turnRight();
            if (canMove()) {
                move();
            } else {
                turnLeft();
                while (!canMove()) {
                    turnLeft();
                }
                move();
            }
        }
    }

    public void eggTrailToNest() {
        while(!onNest()) {
            if(eggAhead() || nestAhead()) {
                move();
                turnLeft();
            } else {
                turnRight();
            }
        }
    }

    public void faceEast()
    {
        while(getDirection() != EAST){
            turnRight();
        }
    }

    public void faceWest()
    {
        while(getDirection() != WEST){
            turnRight();
        }
    }

    public void faceSouth()
    {
        while(getDirection() != SOUTH){
            turnRight();
        }
    }

    public void faceNorth()
    {
        while(getDirection() != NORTH){
            turnRight();
        }
    }

    public void faceDirection(int direction) {
        if (direction >= 0 && direction <= 3) {
            while (getDirection() != direction) {
                turnRight();
            }
        }
    }

    /**
     * zolang niet op de nest
     * draai naar rechts
     * als er geen hek voor je staat en niet naar de locatie dan lopen
     * loop
     * anders naar links en als er dan weer geen hek voor je staat loop en als dat weer niet is moet je gaan lopen en daarna weer naar links 
     * 
     */
    public void findMaze()
    {
        while (!onNest())
        {
            turnRight();
            if (!fenceAhead())
            {
                move();
            }
            else
            {
                turnLeft();
                if (!fenceAhead())
                {
                    move();
                }
                else
                {
                    turnLeft();
                    if (!fenceAhead())
                    {
                        move();
                    }
                    else
                    {
                        turnLeft();
                        move();
                    }
                }
            }
        }
    }

    public boolean validCoordinates(int x, int y)
    {
        if (x < 0 || x >= getWorld().getWidth() ||
        y < 0 || y >= getWorld().getHeight())
        {
            showError("Invalid coordinates");
            return false;
        }

        return true;
    }

    public void goToLocation(int x, int y)
    {
        if (!validCoordinates(x, y))
        {
            return;
        }

        if (getX() < x)
        {
            faceDirection(EAST);
            jump(x - getX());
        }
        else if (getX() > x)
        {
            faceDirection(WEST);
            jump(getX() - x);
        }

        if (getY() < y)
        {
            faceDirection(SOUTH);
            jump(y - getY());
        }
        else if (getY() > y)
        {
            faceDirection(NORTH);
            jump(getY() - y);
        }
    }

    public int countEggsInRow()
    {
        int aantalEieren = 0;

        if (onEgg())
        {
            aantalEieren++;
        }

        while (!borderAhead())
        {
            move();

            if (onEgg())
            {
                aantalEieren++;
            }
        }

        goBackToStartOfRowAndFaceBack();

        return aantalEieren;
    }
    public void layTrailOfEggs(int n) {
    if (n <= 0) {
        showError("Aantal moet groter zijn dan 0");
    } else {
        int aantalGelegd = 0;
        while (aantalGelegd < n) {
            layEgg();
            aantalGelegd++;
            if (aantalGelegd < n) {
                move();
            }
        }
    }
}
public int countEggsInWorld() {
    int totaal = 0;
    int rij = 0;
    while (rij < getWorld().getHeight()) {
        goToLocation(0, rij);
        faceEast();
        totaal = totaal + countEggsInRow();
        rij++;
    }
    goToLocation(0, 0);
    faceEast();
    return totaal;
}
    
} 


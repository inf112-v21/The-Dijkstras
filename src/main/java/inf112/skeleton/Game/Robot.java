package inf112.skeleton.Game;
import inf112.skeleton.grid.Directions;
import inf112.skeleton.grid.GameBoard;
import inf112.skeleton.grid.Location;

    /**
     * Robot Class
     * - Has 9 health. Loss life if 0
     * TODO KOBLE MOT GAMEBOARD
     */
    public class Robot implements IRobot{
        private int health;
        private Directions direction;

        public Robot(Location loc) {
            this.health =9;
            this.direction = Directions.NORTH;
        }


        public int getHealth() {
            return health;
        }

        public void addDamage(int damage){
            health -= damage;
        }

        public Directions getDirection(){
            return this.direction;
        }

        public void setDirection(Directions direction) {
            this.direction = direction;
        }


        /**
         * Rotates robot
         *
         * @param amountToRotate
         * Rotate 90 dg right = + 1
         * Rotate 90 dg left = - 1
         * Backwards = +2
         */
        public void rotate(int amountToRotate){
            this.direction = direction.rotate(amountToRotate);
        }


    }

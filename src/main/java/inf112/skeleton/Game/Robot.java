package inf112.skeleton.Game;
import inf112.skeleton.grid.Directions;
import inf112.skeleton.grid.Location;

    /**
     * Robot Class
     * - Has 9 health. Loss life if 0
     * - StartPosition = (0,0)
     */
    public class Robot {
        private final int layer = 4;
        private int health;
        private Location position;
        private Directions direction;

        public Robot(Location loc) {

            this.health =9;
            this.position= loc;
            this.direction = Directions.NORTH;
        }

        public int getHealth() {
            return health;
        }

        public Location getPosition() {
            return this.position;
        }
        public void getDamage(int damage){
            health-= damage;

        }


        public void moveForward(){
            this.position = this.position.move(direction);
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

        public Directions getDirection() {
            return direction;
        }

        public void reverse() {
            this.position = this.position.move(direction.opposite());
        }
    }

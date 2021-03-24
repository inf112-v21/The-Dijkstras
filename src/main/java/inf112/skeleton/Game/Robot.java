package inf112.skeleton.Game;
import inf112.skeleton.grid.Directions;


    /**
     * Robot Class
     * - Has 9 health. Loss life if 0
     *
     */
    public class Robot implements IRobot{
        private int health;
        private Directions direction;
        private String name;
        public Robot() {
            this.health =9;
            this.direction = Directions.NORTH;
            this.name= "Default name";
        }
        public Robot(String name) {
            this.health =9;
            this.direction = Directions.NORTH;
            this.name= name;
        }
        public String getName(){
            return name;
        }

        public void resetHealth() {
            this.health = 9;
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
        @Override
        public String toString(){
            return this.name;
        }
    }
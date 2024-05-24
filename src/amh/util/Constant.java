package amh.util;

public class Constant {

    public static class Directions{
        public static final byte LEFT = 0;
        public static final byte UP = 1;
        public static final byte RIGHT = 2;
        public static final byte DOWN = 3;
    }

    public static class PlayerConstant{
        public static final byte ATTACK_3 = 0;
        public static final byte ATTACK_2 = 1;
        public static final byte ATTACK_1 = 2;
        public static final byte IDLE = 3;
        public static final byte RUNNING = 4;
        public static final byte JUMPING = 5;
        public static final byte DOUBLE_JUMPING = 6;
        public static final byte CLIMBING = 7;
        public static final byte DIE = 8;
        public static final byte HURT = 9;
        public static final byte RUN_ATTACK = 10;
        public static final byte KICK = 11;
        public static final byte FALL = 12;

        public static int GetSpriteAmount(int playerAction) {
            switch (playerAction) {
                case ATTACK_3:
                case ATTACK_2:
                    return 8;
                case ATTACK_1:
                case RUNNING:
                case DOUBLE_JUMPING:
                case CLIMBING:
                case DIE:
                case RUN_ATTACK:
                case KICK:
                    return 6;
                case IDLE:
                case JUMPING:
                    return 4;
                case HURT:
                    return 2;
                case FALL:
                default:
                    return 1;
            }
        }
    }
}

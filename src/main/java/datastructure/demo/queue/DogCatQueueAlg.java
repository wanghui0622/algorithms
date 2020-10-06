package datastructure.demo.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 猫狗队列
 * @author 王辉
 * @create 2020-07-26 11:33
 * @Description
 * 实现功能：
 * 调用add方法将猫狗放入队列
 * 调用pollAll方法将队列中实例按照入队顺序依次弹出
 * 调用pollDog方法按照入队顺序依次弹出dog
 * 调用pollCat方法按照入队顺序依次弹出cat
 * 调用isEmpty方法检查队列是否为空
 * 调用isDogEmpty检查队列中是否有dog
 * 调用isCatEmpty检查队列中是否有cat
 *
 * 实现思路：
 * 用两个Queue定义一个猫狗队列，一个queue(dogQ)用于存放dog,另一个（catQ）用于存放cat。
 * 入队时，根据pet的类型（cat or dog）决定将pet放入哪个队列
 * 出队时，根据pet的类型（cat or dog）决定哪个队列中的pet出队
 * 为了保证出队顺序的正确性，需要记录每个pet入队的顺序（PetEnterNode.index）。
 */
public class DogCatQueueAlg {

    //定义宠物类
    public static class Pet {
        private String type;

        public Pet(String type) {
            this.type = type;
        }

        public String getPetType() {
            return this.type;
        }
    }

    //定义Dog类，继承宠物类
    public static class Dog extends Pet {
        public Dog() {
            super("dog");
        }
    }

    //定义Cat类，继承宠物类
    public static class Cat extends Pet {
        public Cat() {
            super("cat");
        }
    }

    //定义宠物队列节点
    public static class PetEnterNode {
        private Pet pet;        //队列中节点宠物
        private long index;     //宠物在队列中的节点位置

        public PetEnterNode(Pet pet, long index) {
            this.pet = pet;
            this.index = index;
        }

        public Pet getPet() {
            return this.pet;
        }

        public long getIndex() {
            return this.index;
        }

        public String getEnterPetType() {
            return this.pet.getPetType();
        }
    }

    //定义猫狗队列
    public static class DogCatQueue {
        private Queue<PetEnterNode> dogQ;       //dog实例入队队列
        private Queue<PetEnterNode> catQ;       //cat实例入队队列
        private long count;                     //猫狗队列计数器

        //构造函数初始化猫狗队列
        public DogCatQueue() {
            this.dogQ = new LinkedList<>();
            this.catQ = new LinkedList<>();
            this.count = 0;
        }

        //向队列中添加实例
        public void add(Pet pet) {
            if (pet.getPetType().equals("dog")) {
                this.dogQ.add(new PetEnterNode(pet, this.count++));
            } else if (pet.getPetType().equals("cat")) {
                this.catQ.add(new PetEnterNode(pet, this.count++));
            } else {
                throw new RuntimeException("err, not dog or cat");
            }
        }

        /**
         * 出队所有实例
         * 出队规则：
         * 猫狗队列都不空时：出队索引小的队列中的队顶元素
         * 猫狗队列其中一个为空时，依次出队非空队列中的元素
         */
        public Pet pollAll() {
            if (!this.dogQ.isEmpty() && !this.catQ.isEmpty()) {
                if (this.dogQ.peek().getIndex() < this.catQ.peek().getIndex()) {
                    return this.dogQ.poll().getPet();
                } else {
                    return this.catQ.poll().getPet();
                }
            } else if (!this.dogQ.isEmpty()) {
                return this.dogQ.poll().getPet();
            } else if (!this.catQ.isEmpty()) {
                return this.catQ.poll().getPet();
            } else {
                throw new RuntimeException("err, queue is empty!");
            }
        }

        // poll dog queue
        public Dog pollDog() {
            if (!this.isDogQueueEmpty()) {
                return (Dog) this.dogQ.poll().getPet();
            } else {
                throw new RuntimeException("Dog queue is empty!");
            }
        }

        // poll cat queue
        public Cat pollCat() {
            if (!this.isCatQueueEmpty()) {
                return (Cat) this.catQ.poll().getPet();
            } else
                throw new RuntimeException("Cat queue is empty!");
        }

        //dog queue and cat queue is empty?
        public boolean isEmpty() {
            return this.dogQ.isEmpty() && this.catQ.isEmpty();
        }

        //dog queue is empty?
        public boolean isDogQueueEmpty() {
            return this.dogQ.isEmpty();
        }

        //cat queue is empty?
        public boolean isCatQueueEmpty() {
            return this.catQ.isEmpty();
        }

    }

    public static void main(String[] args) {
        DogCatQueue test = new DogCatQueue();

        Pet dog1 = new Dog();
        Pet cat1 = new Cat();
        Pet dog2 = new Dog();
        Pet cat2 = new Cat();
        Pet dog3 = new Dog();
        Pet cat3 = new Cat();

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);
        while (!test.isDogQueueEmpty()) {
            System.out.println(test.pollDog().getPetType());
        }
        while (!test.isEmpty()) {
            System.out.println(test.pollAll().getPetType());
        }
    }
}

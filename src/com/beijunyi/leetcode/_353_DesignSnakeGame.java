package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not
 * familiar with the game.
 *
 * The snake is initially positioned at the top left corner (0,0) with length = 1 unit.
 *
 * You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's
 * score both increase by 1.
 *
 * Each food appears one by one on the screen. For example, the second food will not appear until the first food was
 * eaten by the snake.
 *
 * When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.
 *
 * Example:
 *   Given width = 3, height = 2, and food = [[1,2],[0,1]].
 *   Snake snake = new Snake(width, height, food);
 *   Initially the snake appears at position (0,0) and the food at (1,2).
 *
 *   |S| | |
 *   | | |F|
 *   snake.move("R"); -> Returns 0
 *
 *   | |S| |
 *   | | |F|
 *   snake.move("D"); -> Returns 0
 *
 *   | | | |
 *   | |S|F|
 *   snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )
 *
 *   | |F| |
 *   | |S|S|
 *   snake.move("U"); -> Returns 1
 *
 *   | |F|S|
 *   | | |S|
 *   snake.move("L"); -> Returns 2 (Snake eats the second food)
 *
 *   | |S|S|
 *   | | |S|
 *   snake.move("U"); -> Returns -1 (Game over because snake collides with border)
 */
public class _353_DesignSnakeGame implements Medium, PremiumQuestion {

  public interface Solution {
    /** Initialize your data structure here.
     @param width - screen width
     @param height - screen height
     @param food - A list of food positions
     E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    void init(int width, int height, int[][] food);

    /** Moves the snake.
     @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     @return The game's score after the move. Return -1 if game over.
     Game over when snake crosses the screen boundary or bites its body. */
    int move(String direction);
  }

  public static class Solution1 implements Solution {

    private Deque<Integer> snakeBody;
    private Set<Integer> occupied;
    private int width;
    private int height;
    private int[][] food;
    private int count = 0;
    private boolean gameOver;

    @Override
    public void init(int width, int height, int[][] food) {
      this.width = width;
      this.height = height;
      this.food = food;
      snakeBody = new LinkedList<>();
      snakeBody.addLast(0);
      occupied = new HashSet<>(); occupied.add(0);
      count = 0;
      gameOver = false;
    }

    @Override
    public int move(String direction) {
      if(gameOver) return -1;
      int curValue = snakeBody.getFirst();
      int lastValue = snakeBody.removeLast();
      occupied.remove(lastValue);
      int[] head = new int[2];
      head[0] = curValue / width;
      head[1] = curValue % width;
      switch(direction) {
        case "U": head[0]--;break;
        case "L": head[1]--;break;
        case "R": head[1]++;break;
        case "D": head[0]++;break;
      }
      int newHead = head[0] * width + head[1];
      if(head[0] < 0 || head[1] < 0 || head[0] >= height || head[1] >= width || occupied.contains(newHead)) {
        gameOver = true;
        return -1;
      }
      snakeBody.addFirst(newHead);
      occupied.add(newHead);
      if(count < food.length && food[count][0] == head[0] && food[count][1] == head[1]) {
        snakeBody.addLast(lastValue);
        occupied.add(lastValue);
        count++;
      }
      return snakeBody.size() - 1;
    }

  }

  public static class Solution2 implements Solution {

    private int width;
    private int height;
    private char[][] board;
    private int[][] food;
    private int foodIndex;
    private int[] head;
    private int[] tail;
    private int length;

    @Override
    public void init(int width, int height, int[][] food) {
      this.width = width;
      this.height = height;
      this.food = food;
      board = new char[height][width];
      foodIndex = 0;
      if(width > 0 && height > 0) {
        board[0][0] = 'S';
        head = new int[] {0, 0};
        tail = new int[] {0, 0};
        length = 1;
      }
    }

    @Override
    public int move(String direction) {
      char dir = direction.charAt(0);
      board[head[0]][head[1]] = dir;
      applyMovement(head, dir);
      if(isHeadOutside()) return -1;
      if(isHeadOnBody()) return -1;
      boolean grow = isHeadOnFood();
      moveBody(grow);
      return length - 1;
    }

    private void applyMovement(int[] part, char direction) {
      switch(direction) {
        case 'U':
          part[0]--;
          break;
        case 'D':
          part[0]++;
          break;
        case 'L':
          part[1]--;
          break;
        case 'R':
          part[1]++;
          break;
      }
    }

    private boolean isHeadOutside() {
      return head[0] < 0 || head[0] >= height || head[1] < 0 || head[1] >= width;
    }

    private boolean isHeadOnBody() {
      return board[head[0]][head[1]] != 0 && !(head[0] == tail[0] && head[1] == tail[1]);
    }

    private boolean isHeadOnFood() {
      if(foodIndex < food.length && head[0] == food[foodIndex][0] && head[1] == food[foodIndex][1]) {
        foodIndex++;
        return true;
      }
      return false;
    }

    private void moveBody(boolean grow) {
      if(!grow) {
        char tailDirection = board[tail[0]][tail[1]];
        board[tail[0]][tail[1]] = 0;
        applyMovement(tail, tailDirection);
      } else {
        length++;
      }
      board[head[0]][head[1]] = 'S';
    }

  }

  public static void main(String args[]) {
    int width;
    int height;
    int[][] food;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      width = 3;
      height = 2;
      food = new int[][] {
        {1, 2}, {0, 1}
      };
      s.init(width, height, food);
      System.out.println(s.move("R"));
      System.out.println(s.move("D"));
      System.out.println(s.move("R"));
      System.out.println(s.move("U"));
      System.out.println(s.move("L"));
      System.out.println(s.move("U"));
      System.out.println();

      width = 3;
      height = 3;
      food = new int[][] {
        {0, 1}, {0, 2}, {1, 2}, {2, 2}, {2, 1}, {2, 0}, {1, 0}
      };
      s.init(width, height, food);
      System.out.println(s.move("R")); // 1
      System.out.println(s.move("R")); // 2
      System.out.println(s.move("D")); // 3
      System.out.println(s.move("D")); // 4
      System.out.println(s.move("L")); // 5
      System.out.println(s.move("L")); // 6
      System.out.println(s.move("U")); // 7
      System.out.println(s.move("U")); // 7
      System.out.println(s.move("R")); // 7
      System.out.println(s.move("R")); // 7
      System.out.println(s.move("D")); // 7
      System.out.println(s.move("D")); // 7
      System.out.println(s.move("L")); // 7
      System.out.println(s.move("L")); // 7
      System.out.println(s.move("U")); // 7
      System.out.println(s.move("R")); // 7
      System.out.println(s.move("U")); // 7
      System.out.println(s.move("L")); // 7
      System.out.println(s.move("D")); // -1
      System.out.println();

      width = 3;
      height = 3;
      food = new int[][] {
        {2, 0}, {0, 0}, {0, 2}, {2, 2}
      };
      s.init(width, height, food);
      System.out.println(s.move("D"));
      System.out.println(s.move("D"));
      System.out.println(s.move("R"));
      System.out.println(s.move("U"));
      System.out.println(s.move("U"));
      System.out.println(s.move("L"));
      System.out.println(s.move("D"));
      System.out.println(s.move("R"));
      System.out.println(s.move("R"));
      System.out.println(s.move("U"));
      System.out.println(s.move("L"));
      System.out.println(s.move("D"));
      System.out.println();
    }
  }

}

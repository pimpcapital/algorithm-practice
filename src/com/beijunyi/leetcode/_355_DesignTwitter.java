package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see
 * the 10 most recent tweets in the user's news feed. Your design should support the following methods:
 *
 * postTweet(userId, tweetId): Compose a new tweet.
 * getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must
 * be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least
 * recent.
 *
 * follow(followerId, followeeId): Follower follows a followee.
 * unfollow(followerId, followeeId): Follower unfollows a followee.
 *
 * Example:
 *   Twitter twitter = new Twitter();
 *
 *   // User 1 posts a new tweet (id = 5).
 *   twitter.postTweet(1, 5);
 *
 *   // User 1's news feed should return a list with 1 tweet id -> [5].
 *   twitter.getNewsFeed(1);
 *
 *   // User 1 follows user 2.
 *   twitter.follow(1, 2);
 *
 *   // User 2 posts a new tweet (id = 6).
 *   twitter.postTweet(2, 6);
 *
 *   // User 1's news feed should return a list with 2 tweet ids -> [6, 5].
 *   // Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
 *   twitter.getNewsFeed(1);
 *
 *   // User 1 unfollows user 2.
 *   twitter.unfollow(1, 2);
 *
 *   // User 1's news feed should return a list with 1 tweet id -> [5],
 *   // since user 1 is no longer following user 2.
 *   twitter.getNewsFeed(1);
 */
public class _355_DesignTwitter implements Medium {

  public interface Solution {
    /** Initialize your data structure here. */
    void init();

    /** Compose a new tweet. */
    void postTweet(int userId, int tweetId);

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by
     * users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    List<Integer> getNewsFeed(int userId);

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    void follow(int followerId, int followeeId);

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    void unfollow(int followerId, int followeeId);
  }

  public static class Solution1 implements Solution {

    private Map<Integer, Set<Integer>> fans;
    private Map<Integer, LinkedList<Tweet>> tweets;
    private int cnt;

    @Override
    public void init() {
      fans = new HashMap<>();
      tweets = new HashMap<>();
      cnt = 0;
    }

    @Override
    public void postTweet(int userId, int tweetId) {
      if (!fans.containsKey(userId)) fans.put(userId, new HashSet<>());
      fans.get(userId).add(userId);
      if (!tweets.containsKey(userId)) tweets.put(userId, new LinkedList<>());
      tweets.get(userId).addFirst(new Tweet(cnt++, tweetId));
    }

    @Override
    public List<Integer> getNewsFeed(int userId) {
      if (!fans.containsKey(userId)) return new LinkedList<>();
      PriorityQueue<Tweet> feed = new PriorityQueue<>((t1, t2) -> t2.time - t1.time);
      fans.get(userId).stream()
        .filter(followee -> tweets.containsKey(followee))
        .forEach(followee -> tweets.get(followee).forEach(feed::add));
      List<Integer> res = new LinkedList<>();
      while(feed.size() > 0 && res.size() < 10) res.add(feed.poll().id);
      return res;
    }

    @Override
    public void follow(int followerId, int followeeId) {
      if (!fans.containsKey(followerId)) fans.put(followerId, new HashSet<>());
      fans.get(followerId).add(followeeId);
    }

    @Override
    public void unfollow(int followerId, int followeeId) {
      if (fans.containsKey(followerId) && followeeId != followerId) fans.get(followerId).remove(followeeId);
    }

    private static class Tweet {
      private final int time;
      private final int id;

      Tweet(int time, int id) {
        this.time = time;
        this.id = id;
      }
    }
  }

  public static void main(String args[]) {
    for(Solution twitter : Arrays.asList(new Solution1())) {
      twitter.init();
      twitter.postTweet(1, 5);
      System.out.println(twitter.getNewsFeed(1));
      twitter.follow(1, 2);
      twitter.postTweet(2, 6);
      System.out.println(twitter.getNewsFeed(1));
      twitter.unfollow(1, 2);
      System.out.println(twitter.getNewsFeed(1));
    }
  }

}

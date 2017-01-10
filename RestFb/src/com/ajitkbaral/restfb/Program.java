/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajitkbaral.restfb;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.Facebook;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.Page;
import com.restfb.types.Post;
import com.restfb.types.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 *
 * @author Ajit Kumar Baral
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	
	//Generate Access Token from Facebook Graph API here
		String accessToken = "";
		
        // TODO code application logic here
        FacebookClient facebookClient = new DefaultFacebookClient(accessToken);
        User user = facebookClient.fetchObject("me", User.class);
        System.out.println(user.getId());

        FacebookType publishMessageResponse
                = facebookClient.publish("me/feed", FacebookType.class,
                        Parameter.with("message", "RestFB test"));

        System.out.println("Published message ID: " + publishMessageResponse.getId());

        // Some Post from the GoT Fanpage with likes and comments total count
        Post post = facebookClient.fetchObject("74133697733_10152424266332734",
                Post.class,
                Parameter.with("fields", "from,to,likes.summary(true),comments.summary(true)"));

        System.out.println("Likes count: " + post.getLikesCount());
        System.out.println("Likes count (from Likes): " + post.getLikes().getTotalCount());
        System.out.println("Comments count: " + post.getComments().getTotalCount());

    }

}

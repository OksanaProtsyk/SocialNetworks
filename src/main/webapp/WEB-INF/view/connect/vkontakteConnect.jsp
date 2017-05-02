<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: okpr0814
  Date: 5/2/2017
  Time: 9:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Connecting facebook
<form th:action="@{/connect/vkontakte}" method="POST">
    <input type="hidden" name="_csrf" th:th:value="${_csrf.token}" />
    <input type="hidden" name="defaultScope" value="read_stream,user_posts,user_photos,friends_about_me,
    user_friends,
friends_activities,
friends_birthday,
friends_checkins,
friends_education_history,
friends_events,
friends_games_activity,
friends_groups,
friends_hometown,
friends_interests,
friends_likes,
friends_location,
friends_notes,
friends_online_presence,
friends_photo_video_tags,
friends_photos,
friends_relationship_details,
friends_relationships,
friends_religion_politics,
friends_status,
friends_subscriptions,
friends_videos,
friends_website,
friends_work_history" />
    <div class="formInfo">
        <p>You aren't connected to Facebook yet. Click the button to connect Spring Social Showcase with your Facebook account.</p>
    </div>
    <p><button type="submit"><img src="/resources/static/social/vk/connect_light_medium_short.gif"/>Vkontakte</button></p>
    <label for="postToWall"><input id="postToWall" type="checkbox" name="postToWall" /> Tell your friends about Spring Social Showcase on your Facebook wall</label>
</form>
</body>
</html>

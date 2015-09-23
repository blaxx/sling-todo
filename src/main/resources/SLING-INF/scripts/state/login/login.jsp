<%@include file="/apps/todo/global.jsp"%>
<p>You must be logged in to use this application.</p>
<div class="login-controls">
    <form ng-submit="loginform.submit()">
        <input type="text" name="j_username" placeholder="User Name" required ng-model="loginform.username" />
        <input type="password" name="j_password" placeholder="Password" required ng-model="loginform.password" />
        <button type="submit">Sign In</button>
    </form>
</div>
<%@include file="WEB-INF/i18n.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>JSP/JSTL i18n demo</title>
</head>
<body>
<form action="/signIn" method="get">
    <select id="language" name="language">
        <option value="en_US" ${language == 'en_US' ? 'selected' : ''}>English</option>
        <option value="ru_RU" ${language == 'ru_RU' ? 'selected' : ''}>Russian</option>
    </select>
    <button type="submit"></button>
</form>
</body>
</html>
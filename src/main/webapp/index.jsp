<html>
<body>
<h2>Hello World!</h2>


SpringMVC upload file
<form name="form1" action="/manage/product/upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="uploadFile"/>
    <input type="submit" value="springmvc上传文件"/>
</form>

richtext upload file
<form name="form1" action="/manage/product/richtext_img_upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="uploadFile"/>
    <input type="submit" value="richtext上传文件"/>
</form>
</body>
</html>

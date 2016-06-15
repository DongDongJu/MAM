<?php
$con = mysqli_connect("127.0.0.1","root","qkrtpwlsWkd","MAM");

if(mysqli_connect_errno($con))
{
        echo "Failed : ". mysqli_connect_error();
}
mysqli_set_charset($con,"utf8");
$res = mysqli_query($con,"select * from SongList");
$result = array();

while($row = mysqli_fetch_array($res)){
	array_push($result,array('idx'=>$row[0],'songName'=>$row[1]));
} 
echo json_encode(array("result"=>$result));

mysqli_close($con);
?>



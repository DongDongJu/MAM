<?php
$con = mysqli_connect("127.0.0.1","root","qkrtpwlsWkd","DongDongLogger");

if(mysqli_connect_errno($con))
{
        echo "Failed : ". mysqli_connect_error();
}
mysqli_set_charset($con,"utf8");
$res = mysqli_query($con,"select * from Logger");
$result = array();

while($row = mysqli_fetch_array($res)){
	array_push($result,array('place'=>$row[0],'lat'=>$row[1],'longi'=>$row[2]));
} 
echo json_encode(array("result"=>$result));

mysqli_close($con);
?>



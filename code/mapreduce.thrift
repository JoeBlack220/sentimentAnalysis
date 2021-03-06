struct MapResult{
	1:string filename,
	2:double score
}
struct ClientResult {
	1: string fileOrder,
	2: i32 time
}
 
service MapService {
	bool accept(1: i32 nodeID, 2: i32 mode),
	MapResult mapping(1:string fileUri),
	string sort(1: list<MapResult> scores, 2:string inputDir)
}

service AssignService {
	ClientResult assign(1: string folderAddress, 2: i32 mode)
}

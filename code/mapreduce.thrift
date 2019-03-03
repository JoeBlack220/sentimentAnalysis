struct MapResult{
	1:string filename,
	2:double score
}


service MapService {
	bool accept(),
	MapResult mapping(1:string fileUri)
}

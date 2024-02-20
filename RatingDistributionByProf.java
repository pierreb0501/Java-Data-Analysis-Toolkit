package finalproject;

public class RatingDistributionByProf extends DataAnalyzer {

	MyHashTable<String, int[]> distByProf;
    public RatingDistributionByProf(Parser p) {
        super(p);
    }

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {
		// ADD YOUR CODE BELOW THIS

		MyHashTable<String, Integer> distByKeyword = new MyHashTable<>();
		int[] arr = distByProf.get(keyword.replaceAll("\\s+", " ").toLowerCase());

		if(arr==null)
			return null;

		distByKeyword.put("1", arr[0]);
		distByKeyword.put("2", arr[1]);
		distByKeyword.put("3", arr[2]);
		distByKeyword.put("4", arr[3]);
		distByKeyword.put("5", arr[4]);

		return distByKeyword;
		//ADD YOUR CODE ABOVE THIS
	}

	@Override
	public void extractInformation() {
		// ADD YOUR CODE BELOW THIS

		MyHashTable<String, int[]> distByProf = new MyHashTable<>();

		int professorIndex = parser.fields.get("professor_name");
		int ratingIndex = parser.fields.get("student_star");

		for(String[] s :parser.data){

			//todo process string better
			String professor = s[professorIndex].replaceAll("\\s+", " ").toLowerCase();
			double ratingDouble = Double.parseDouble(s[ratingIndex]);
			int rating = (int) (ratingDouble);

			int[] ratings = distByProf.get(professor);

			if(ratings == null) {
				ratings = new int[5];
				ratings[rating-1] = 1;
				distByProf.put(professor, ratings);
			} else {
				ratings[rating-1]++;
			}
		}

		this.distByProf = distByProf;
		//ADD YOUR CODE ABOVE THIS
	}

}

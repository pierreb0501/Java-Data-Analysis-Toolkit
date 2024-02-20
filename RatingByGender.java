package finalproject;

public class RatingByGender extends DataAnalyzer{

	MyHashTable<String, int[]> distByRating;


	public RatingByGender(Parser p) {
		super(p);
	}

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {
		// ADD YOUR CODE BELOW THIS

		MyHashTable<String, Integer> distByKeyword = new MyHashTable<>();

		 String tmp = keyword.replaceAll(" ", "");
		String[] words = tmp.split(",");
		int[] arr = this.distByRating.get(words[0]);

		if(arr==null)
			return null;

		if(words[1].equalsIgnoreCase("quality")){
			distByKeyword.put("1", arr[0]);
			distByKeyword.put("2", arr[1]);
			distByKeyword.put("3", arr[2]);
			distByKeyword.put("4", arr[3]);
			distByKeyword.put("5", arr[4]);
		}else{
			distByKeyword.put("1", arr[5]);
			distByKeyword.put("2", arr[6]);
			distByKeyword.put("3", arr[7]);
			distByKeyword.put("4", arr[8]);
			distByKeyword.put("5", arr[9]);
		}



		return distByKeyword;

		//ADD YOUR CODE ABOVE THIS
	}

	@Override
	public void extractInformation() {
		// ADD YOUR CODE BELOW THIS

		MyHashTable<String, int[]> distByRating = new MyHashTable<>();

		int ratingIndex = parser.fields.get("student_star");
		int genderIndex = parser.fields.get("gender");
		int diffIndex = parser.fields.get("student_difficult");

		for(String[] s :parser.data){

			double diffDouble = Double.parseDouble(s[diffIndex]);
			int diff = (int) (diffDouble) + 4;

			double ratingDouble = Double.parseDouble(s[ratingIndex]);
			int rating = (int) (ratingDouble);

			String gender = s[genderIndex];

			int[] ratings = distByRating.get(gender);

			if(ratings == null) {
				ratings = new int[10];
				ratings[rating-1] = 1;
				ratings[diff] = 1;
				distByRating.put(gender, ratings);
			} else {
				ratings[rating-1]++;
				ratings[diff]++;
			}
		}

		this.distByRating = distByRating;
		//ADD YOUR CODE ABOVE THIS

		//ADD YOUR CODE ABOVE THIS
	}
}

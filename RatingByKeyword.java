package finalproject;

public class RatingByKeyword extends DataAnalyzer {

	MyHashTable<String, int[]> distByKeyword;

    public RatingByKeyword(Parser p) {
        super(p);
    }

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {
		// ADD YOUR CODE BELOW THIS
		MyHashTable<String, Integer> distByKeyword = new MyHashTable<>();
		int[] arr = this.distByKeyword.get(keyword);

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
		MyHashTable<String, int[]> distByKeyword = new MyHashTable<>();

		int commentIndex = this.parser.fields.get("comments");
		int ratingIndex = parser.fields.get("student_star");

		for (String[] s: this.parser.data) {
			MyHashTable<String, Integer> doubleCounting = new MyHashTable<>();
			String commentWords[] = s[commentIndex].toLowerCase().replaceAll("[^a-z']", " ").split("\\s+");
			double ratingDouble = Double.parseDouble(s[ratingIndex]);
			int rating = (int) (ratingDouble);

			for (String word : commentWords) {
				Integer doubleC = doubleCounting.get(word);

				if (doubleC == null) {
					doubleCounting.put(word, 1);

					int[] ratings = distByKeyword.get(word);

					if (ratings == null) {
						ratings = new int[5];
						ratings[rating - 1] = 1;
						distByKeyword.put(word, ratings);
					} else {
						ratings[rating - 1]++;
					}
				}
			}
		}

		this.distByKeyword = distByKeyword;

		//ADD YOUR CODE ABOVE THIS
	}
}

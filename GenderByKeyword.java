package finalproject;

public class GenderByKeyword extends DataAnalyzer {

	MyHashTable<String, int[]> distByGender;

	public GenderByKeyword(Parser p) {
		super(p);
	}

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {
		// ADD YOUR CODE BELOW THIS
		MyHashTable<String, Integer> distByKeyword = new MyHashTable<>();


		int[] arr = distByGender.get(keyword);

		if(arr==null){
			return null;
		}

		distByKeyword.put("M", arr[0]);
		distByKeyword.put("F", arr[1]);
		distByKeyword.put("X", arr[2]);

		return distByKeyword;
		//ADD YOUR CODE ABOVE THIS
	}

	@Override
	public void extractInformation() {
		// ADD YOUR CODE BELOW THIS

		MyHashTable<String, int[]> distByGender = new MyHashTable<>();

		int commentIndex = this.parser.fields.get("comments");
		int genderIndex = this.parser.fields.get("gender");

		int indexM = 0;
		int indexF = 1;
		int indexX = 2;

		for (String[] s: this.parser.data){
			MyHashTable<String, Integer> doubleCounting = new MyHashTable<>();
			String commentWords[] = s[commentIndex].toLowerCase().replaceAll("[^a-z']", " ").split("\\s+");
			String gender = s[genderIndex];
			int indexS = 0;

			switch (gender) {
				case "M" -> indexS = indexM;
				case "F" -> indexS = indexF;
				case "X" -> indexS = indexX;
				default -> {
				}
			}

			for(String word: commentWords){
				Integer doubleC = doubleCounting.get(word);

				if(doubleC==null) {
					doubleCounting.put(word, 1);

					int [] count = distByGender.get(word);

					if(count == null){
						count = new int[3];
						count[indexS] = 1;
						distByGender.put(word, count);
					}else{
						count[indexS]++;
					}
				}
			}

		}

		this.distByGender = distByGender;
		//ADD YOUR CODE ABOVE THIS
	}

}

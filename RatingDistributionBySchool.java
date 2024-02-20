package finalproject;

public class RatingDistributionBySchool extends DataAnalyzer {

	MyHashTable<String, MyHashTable<String, Integer>> distBySchool;

	public RatingDistributionBySchool(Parser p) {
		super(p);
	}

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {
		// ADD YOUR CODE BELOW THIS

		return this.distBySchool.get(keyword.replaceAll("\\s+", " ").toLowerCase());
		//ADD YOUR CODE ABOVE THIS
	}

	@Override
	public void extractInformation() {
		// ADD YOUR CODE BELOW THIS
		MyHashTable<String, MyHashTable<String, Integer>> distBySch = new MyHashTable<>();
		MyHashTable<String, Double[]> profAvg = new MyHashTable<>();

		int schoolIndex = parser.fields.get("school_name");
		int professorIndex = parser.fields.get("professor_name");
		int ratingIndex = parser.fields.get("student_star");

		for(String[] s :parser.data) {

			String professor = s[professorIndex].trim().toLowerCase();

			double ratingDouble = Double.parseDouble(s[ratingIndex]);

			Double[] values = profAvg.get(professor);

			if (values == null) {
				values = new Double[2];
				values[1] = 1.0;
				values[0] = ratingDouble;
				profAvg.put(professor, values);
			} else {
				values[1]++;
				values[0] += ratingDouble;
			}
		}

		for(String[] s: parser.data){

			String school = s[schoolIndex].replaceAll("\\s+", " ").toLowerCase();
			String professor = s[professorIndex].trim().toLowerCase();

			MyHashTable<String, Integer> profTble = distBySch.get(school);
			Double[] values = profAvg.remove(professor);

			if(values != null) {
				if (profTble == null) {
					profTble = new MyHashTable<>();
					profTble.put(professor + "\n" + Math.round(values[0] * 100.0 / values[1]) / 100.0, values[1].intValue());
					distBySch.put(school, profTble);
				} else {
					profTble.put(professor + "\n" + Math.round(values[0] * 100.0 / values[1]) / 100.0, values[1].intValue());
//					distBySch.put(school, profTble);
				}
			}

		}

		this.distBySchool = distBySch;

		//ADD YOUR CODE ABOVE THIS
	}
}


//	MyHashTable<String, Integer> profTble = distBySch.get(school);
//			Integer numOfProfs = null;
//
//			if (profTble != null) {
//				numOfProfs = profTble.get(professor + "\n" + profAvg.get(professor));
//				if (numOfProfs != null)
//					numOfProfs = profTble.get(professor + "\n" + Math.round(profAvg.get(professor) / (double) numOfProfs) * 100.0 / 100.0);
//			}
//
//			double ratingDouble = Double.parseDouble(s[ratingIndex]);
//
//			Double totalRating = profAvg.get(professor);
//
//			if (totalRating == null) {
//				profAvg.put(professor, ratingDouble);
//			} else {
//				profAvg.put(professor, totalRating + ratingDouble);
//			}
//
//			if (numOfProfs == null) {
//				numOfProfs = 1;
//				MyHashTable<String, Integer> distByProf = new MyHashTable<>();
//				String key = professor + "\n" + Math.round(profAvg.get(professor) * 100.0) / 100.0; //todo check this ed
//				distByProf.put(key, numOfProfs);
//				distBySch.put(school, distByProf);
//			} else {
//				numOfProfs++;
//				MyHashTable<String, Integer> distByProf = new MyHashTable<>();
//				String key = professor + "\n" + Math.round(profAvg.get(professor) / (double) numOfProfs) * 100.0 / 100.0;
//				distByProf.put(key, numOfProfs);
//				distBySch.put(school, distByProf);
//			}
//
//
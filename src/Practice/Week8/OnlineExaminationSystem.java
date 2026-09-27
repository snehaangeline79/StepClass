package Practice.Week8;

import java.util.LinkedHashMap;
import java.util.Map;

public class OnlineExaminationSystem {

    static class Student {

        private String name;

        public Student(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    static abstract class Question {

        protected String questionText;
        protected int points;

        public Question(String questionText, int points) {
            this.questionText = questionText;
            this.points = points;
        }

        public abstract boolean evaluate(String answer);

        public int getPoints() {
            return points;
        }
    }

    static class MultipleChoiceQuestion extends Question {

        private String correctAnswer;

        public MultipleChoiceQuestion(
                String questionText,
                int points,
                String correctAnswer) {

            super(questionText, points);
            this.correctAnswer = correctAnswer;
        }

        @Override
        public boolean evaluate(String answer) {
            return correctAnswer.equalsIgnoreCase(answer);
        }
    }

    static class TrueFalseQuestion extends Question {

        private boolean correctAnswer;

        public TrueFalseQuestion(
                String questionText,
                int points,
                boolean correctAnswer) {

            super(questionText, points);
            this.correctAnswer = correctAnswer;
        }

        @Override
        public boolean evaluate(String answer) {
            return Boolean.parseBoolean(answer) == correctAnswer;
        }
    }

    static class ShortAnswerQuestion extends Question {

        private String correctAnswer;

        public ShortAnswerQuestion(
                String questionText,
                int points,
                String correctAnswer) {

            super(questionText, points);
            this.correctAnswer = correctAnswer;
        }

        @Override
        public boolean evaluate(String answer) {
            return correctAnswer.equalsIgnoreCase(answer.trim());
        }
    }

    static class Examination {

        private String examName;
        private Map<Integer, Question> questions = new LinkedHashMap<>();

        public Examination(String examName) {
            this.examName = examName;
        }

        public void addQuestion(int number, Question question) {
            questions.put(number, question);
        }

        public String getExamName() {
            return examName;
        }

        public Map<Integer, Question> getQuestions() {
            return questions;
        }
    }

    static class Attempt {

        private Student student;
        private Examination examination;
        private Map<Integer, String> answers = new LinkedHashMap<>();
        private boolean submitted;

        public Attempt(Student student, Examination examination) {
            this.student = student;
            this.examination = examination;
            this.submitted = false;
        }

        public void recordAnswer(int questionNumber, String answer) {

            if (submitted) {
                System.out.println(
                        "Cannot change answers for a submitted examination."
                );
                return;
            }

            if (!examination.getQuestions().containsKey(questionNumber)) {
                System.out.println("Question does not exist.");
                return;
            }

            answers.put(questionNumber, answer);

            System.out.println(
                    "Answer recorded for Question "
                            + questionNumber + "."
            );
        }

        public void submit() {

            if (submitted) {
                System.out.println("Examination already submitted.");
                return;
            }

            submitted = true;

            System.out.println(
                    examination.getExamName()
                            + " submitted by "
                            + student.getName() + "."
            );

            int totalScore = 0;
            int maximumScore = 0;

            for (Map.Entry<Integer, Question> entry
                    : examination.getQuestions().entrySet()) {

                int questionNumber = entry.getKey();
                Question question = entry.getValue();

                maximumScore += question.getPoints();

                String answer = answers.get(questionNumber);

                boolean correct =
                        answer != null && question.evaluate(answer);

                int score = correct ? question.getPoints() : 0;

                totalScore += score;

                System.out.println(
                        "Question "
                                + questionNumber
                                + ": "
                                + (correct ? "Correct" : "Incorrect")
                                + " ("
                                + score
                                + " points)"
                );
            }

            System.out.println(
                    "Total score: "
                            + totalScore
                            + "/"
                            + maximumScore
            );
        }
    }

    public static void main(String[] args) {

        Student student = new Student("Student 1");

        Examination exam = new Examination("Exam A");

        exam.addQuestion(
                1,
                new MultipleChoiceQuestion(
                        "Which option is correct?",
                        5,
                        "C"
                )
        );

        exam.addQuestion(
                2,
                new TrueFalseQuestion(
                        "Java is a programming language.",
                        5,
                        false
                )
        );

        Attempt attempt = new Attempt(student, exam);

        System.out.println(
                "Exam A started by " + student.getName() + "."
        );

        attempt.recordAnswer(1, "C");
        attempt.recordAnswer(2, "True");

        attempt.submit();

        attempt.recordAnswer(1, "A");
    }
}
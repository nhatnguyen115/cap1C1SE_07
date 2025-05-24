package com.arkdev.z9tkvtu.repository;

import com.arkdev.z9tkvtu.model.UserAccount;
import com.arkdev.z9tkvtu.model.UserTestAttempt;
import com.arkdev.z9tkvtu.util.TestType;
import org.antlr.v4.runtime.misc.MultiMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserTestAttemptRepository extends JpaRepository<UserTestAttempt, Integer> {
    List<UserTestAttempt> findByUser(UserAccount user);

    @Query(value = """
            SELECT
                e.examName AS examName,
                uta.totalScore AS totalScore,
                p.partName AS partName,
                q.content AS content,
                q.options AS options,
                q.explanation AS explanation,
                q.correctAnswer AS correctAnswer,
                ua.selectedAnswer AS selectedAnswer
            FROM Exam e
            JOIN UserTestAttempt uta ON e.id = uta.exam.id
            JOIN UserAnswer ua ON ua.attempt.id = uta.id
            JOIN Question q ON q.id = ua.question.id
            JOIN Part p ON p.id = q.part.id
            WHERE uta.id = :attemptId
            ORDER BY p.orderNumber, q.orderNumber
            """)
    List<Object[]> findByAttemptWithAnswersByAttemptId(@Param("attemptId") Integer attemptId);

    @Query(value = """
        SELECT
            COALESCE(uld.username, uld.email) AS username,
            uta.total_score as totalScore,
            CAST(ROUND(EXTRACT(EPOCH FROM (end_time - start_time)) / 60) AS BIGINT) as totalTime
        FROM user_test_attempt uta
        JOIN user_login_data uld ON uld.user_id = uta.user_id
        WHERE uta.exam_id = :examId AND  uta.complete = true
        ORDER BY uta.total_score DESC
        LIMIT 20;
    """, nativeQuery = true)
    List<Object[]> findByUserOfRank(@Param("examId") Integer examId);

    @Query(value = """
            SELECT
                e.question_count as total_count,
                COUNT(ua.*) as selected_count,
                SUM(CASE WHEN ua.selected_answer = q.correct_answer AND p.grading = 'LISTENING' THEN 1 ELSE 0 END ) AS listening_correct_count,
                SUM(CASE WHEN ua.selected_answer = q.correct_answer AND p.grading = 'READING' THEN 1 ELSE 0 END ) AS reading_correct_count
            FROM part p
            INNER JOIN exam_structure es ON es.part_id = p.part_id
            INNER JOIN exam e ON e.exam_id = es.exam_id
            INNER JOIN user_test_attempt uta ON uta.exam_id = e.exam_id
            INNER JOIN question q ON q.part_id = p.part_id
            INNER JOIN user_answer ua ON ua.question_id = q.question_id AND ua.attempt_id = :attemptId
            WHERE uta.attempt_id = :attemptId group by e.question_count
    """, nativeQuery = true)
    List<Integer[]> answerParameterCalculation(@Param("attemptId") Integer attemptId);

    @Query(value = """
            SELECT
                e.question_count as total_count,
                COUNT(ua.*) as selected_count,
                SUM(CASE WHEN ua.selected_answer = q.correct_answer THEN 1 ELSE 0 END ) AS correct_count
            FROM user_test_attempt uta
            INNER JOIN exam e ON e.exam_id = uta.exam_id
            INNER JOIN exam_structure es ON es.exam_id = e.exam_id
            INNER JOIN part p ON p.part_id = es.part_id
            INNER JOIN question q ON q.part_id = p.part_id
            INNER JOIN user_answer ua ON ua.question_id = q.question_id AND ua.attempt_id = uta.attempt_id
            WHERE uta.attempt_id = :attemptId group by e.question_count
    """, nativeQuery = true)
    List<Integer[]> answerPracticeCalculation(@Param("attemptId") Integer attemptId);

    @Query(value = """
        select listening_score
        from score
        where correct_count = :correctCount
    """, nativeQuery = true)
    Integer findListeningScore(@Param("correctCount") Integer correctCount);

    @Query(value = """
        select reading_score
        from score
        where correct_count = :correctCount
    """, nativeQuery = true)
    Integer findReadingScore(@Param("correctCount") Integer correctCount);

    Optional<UserTestAttempt> findByIdAndCompleteTrue(Integer attemptId);

    Optional<UserTestAttempt> findByUserIdAndExamIdAndExamTestType(UUID userId, Integer examId, TestType examTestType);
}
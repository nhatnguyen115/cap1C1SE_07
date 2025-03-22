import React, { useState, useEffect } from "react";

interface ToeicTestProps {
  toeicTest: {
    title: string;
    level: string;
    listening: {
      part1: { options: string[] }[]; 
      part2: { options: string[] }[]; 
      part3: { questions: { options: string[] }[] }[]; 
      part4: { questions: { options: string[] }[] }[]; 
    };
    reading: {
      part5: { options: string[] }[]; 
      part6: { blanks: { options: string[] }[] }[]; 
      part7: { questions: { options: string[] }[] }[]; 
    };
  };
}

const Navigation: React.FC<ToeicTestProps> = ({ toeicTest }) => {
  const [time, setTime] = useState(0); // Time in seconds
  const [isFullScreen, setIsFullScreen] = useState(false);

  useEffect(() => {
    const timer = setInterval(() => {
      setTime((prevTime) => prevTime + 1);
    }, 1000);

    return () => clearInterval(timer);
  }, []);

  const formatTime = (timeInSeconds: number) => {
    const minutes = Math.floor(timeInSeconds / 60);
    const seconds = timeInSeconds % 60;
    return `${String(minutes).padStart(2, '0')}:${String(seconds).padStart(2, '0')}`;
  };

  const renderQuestionButtons = (numQuestions: number) => {
    return Array.from({ length: numQuestions }, (_, index) => (
      <button
        key={index}
        className="border rounded-md text-center hover:bg-blue-500 hover:text-white text-sm transition-all duration-200"
      >
        {index + 1}
      </button>
    ));
  };

  const toggleFullScreen = () => {
    if (!document.fullscreenElement) {
      document.documentElement.requestFullscreen();
    } else {
      if (document.exitFullscreen) {
        document.exitFullscreen();
      }
    }
    setIsFullScreen(!isFullScreen);
  };

  return (
    <div className="max-w-xs mx-auto p-4 bg-white h-full bottom-5 w-44">
      <div className="space-y-4">
        {/* Time Display */}
        <div className="flex justify-between mb-4">
          <span className="text-sm">Thời gian làm bài:</span>
          <span className="font-semibold text-xl">{formatTime(time)}</span>
        </div>

        {/* Fullscreen Button */}
        <div className="mb-4 flex justify-end">
          <button onClick={toggleFullScreen} className="text-sm text-blue-500 hover:underline">
            {isFullScreen ? "Thoát toàn màn hình" : "Chế độ toàn màn hình"}
          </button>
        </div>

        {/* Listening Part 1 */}
        {toeicTest.listening.part1.length > 0 && (
          <div>
            <h3 className="text-lg font-semibold mb-2">Part 1: Listening</h3>
            <div className="grid grid-cols-4 gap-2 mb-4">
              {renderQuestionButtons(toeicTest.listening.part1.length)}
            </div>
          </div>
        )}

        {/* Listening Part 2 */}
        {toeicTest.listening.part2.length > 0 && (
          <div>
            <h3 className="text-lg font-semibold mb-2">Part 2: Listening</h3>
            <div className="grid grid-cols-4 gap-2 mb-4">
              {renderQuestionButtons(toeicTest.listening.part2.length)}
            </div>
          </div>
        )}

        {/* Listening Part 3 */}
        {toeicTest.listening.part3.length > 0 && (
          <div>
            <h3 className="text-lg font-semibold mb-2">Part 3: Listening</h3>
            {toeicTest.listening.part3.map((item, index) =>
              item.questions.length > 0 ? (
                <div key={index} className="space-y-2">
                  <div className="grid grid-cols-4 gap-2 mb-4">
                    {renderQuestionButtons(item.questions.length)}
                  </div>
                </div>
              ) : null
            )}
          </div>
        )}
                {/* Listening Part 4 */}
                {toeicTest.listening.part4.length > 0 && (
          <div>
            <h3 className="text-lg font-semibold mb-2">Part 4: Listening</h3>
            {toeicTest.listening.part4.map((item, index) =>
              item.questions.length > 0 ? (
                <div key={index} className="space-y-2">
                  <div className="grid grid-cols-4 gap-2 mb-4">
                    {renderQuestionButtons(item.questions.length)}
                  </div>
                </div>
              ) : null
            )}
          </div>
        )}

        {/* Reading Part 5 */}
        {toeicTest.reading.part5.length > 0 && (
          <div>
            <h3 className="text-lg font-semibold mb-2">Part 5: Reading</h3>
            <div className="grid grid-cols-4 gap-2 mb-4">
              {renderQuestionButtons(toeicTest.reading.part5.length)}
            </div>
          </div>
        )}

        {/* Reading Part 6 */}
        {toeicTest.reading.part6.length > 0 && (
          <div>
            <h3 className="text-lg font-semibold mb-2">Part 6: Reading</h3>
            {toeicTest.reading.part6.map((item, index) =>
              item.blanks.length > 0 ? (
                <div key={index} className="space-y-2">
                  <div className="grid grid-cols-4 gap-2 mb-4">
                    {renderQuestionButtons(item.blanks.length)}
                  </div>
                </div>
              ) : null
            )}
          </div>
        )}

        {/* Reading Part 7 */}
        {toeicTest.reading.part7.length > 0 && (
          <div>
            <h3 className="text-lg font-semibold mb-2">Part 7: Reading</h3>
            {toeicTest.reading.part7.map((item, index) =>
              item.questions.length > 0 ? (
                <div key={index} className="space-y-2">
                  <div className="grid grid-cols-4 gap-2 mb-4">
                    {renderQuestionButtons(item.questions.length)}
                  </div>
                </div>
              ) : null
            )}
          </div>
        )}
        {/* Submit Button */}
        <div className="mt-4 text-center">
          <button className="bg-blue-500 text-white p-2 rounded-md w-full hover:bg-blue-600">
            Submit Test
          </button>
        </div>
      </div>
    </div>
  );
};

export default Navigation;

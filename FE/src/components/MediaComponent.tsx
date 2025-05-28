import React from "react";
import {MediaProps} from "../types/media";

const MediaComponent: React.FC<MediaProps> = ({mediaType, url}) => {
    switch (mediaType) {
        case "IMAGE":
            return (
                <img
                    src={url}
                    alt="question visual"
                    className="w-full max-w-xl mb-3 rounded"/>
            );
        case "AUDIO":
            return (
                <audio
                    controls
                    className="w-full max-w-4xl mx-auto mb-3 block"
                    src={url}
                >
                    Trình duyệt của bạn không hỗ trợ audio.
                </audio>
            );
        case "VIDEO":
            return (
                <video
                    controls
                    className="w-full max-w-4xl mx-auto mb-3 block rounded "
                    src={url}
                >
                    Trình duyệt của bạn không hỗ trợ video.
                </video>
            );
        default:
            return null;
    }
}
export default MediaComponent
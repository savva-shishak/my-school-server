;(function () {
    var form = document.forms["lesson"];
    var msgStatus = $("#lesson-msg");

    $('#btn-add-lesson').click(function () {
        msgStatus.html('<div class="alert alert-secondary">Проверка...</div>');

        var data = {
            group: form['group'].value,
            subject: form['subject'].value,
            room: form['room'].value,
            dayWeek: form['day'].value,
            pairNum: form['pair'].value,
        };

        for (var key in data) {
            if (!data[key]) {
                msgStatus.html('<div class="alert alert-warning">Заполните все поля</div>');
                return;
            }
        }

        $.ajax({
            type: "GET",
            url: '/api/lessons/cross?' + $.param(data, true),
            success: success,
            error: function(){
                msgStatus.html('<div class="alert alert-danger">Ошибка</div>');
            }
        });
    });

    function success(lessons) {
        if (lessons.length == 0) {
            drawContinue();
        } else {
            drawCrossLessons(lessons);
        }
    }

    function drawContinue() {
        msgStatus.html(
            '<div class="alert alert-success">Кабинет, группа и учитель в это время свободны</div>' +
            '<button type="submit" class="btn btn-primary">Продолжить</button>');
    }

    function drawCrossLessons(lessons) {
        var template = $("#template-cross-lesson")
        msgStatus.empty();
        msgStatus.append('<div class="col-12">' +
                            '<div class="alert alert-danger">В это время кабинет, группа или учитель будут заняты</div>' +
                         '</div>')

        _.forEach(lessons, function (lesson) {
            var block = template.clone();
            debugger
            block
                .find('.t-teacher')
                .attr("href", "/teachers/" + lesson.subject.teacher.id)
                .text(lesson.subject.name + ' (' + lesson.subject.teacher.name + ')');

            block
                .find('.t-group')
                .attr("href", "/groups/" + lesson.group.id)
                .text(lesson.group.name);

            block
                .find('.t-room')
                .attr("href", "/rooms/" + lesson.room.id)
                .text(lesson.room.name);

            msgStatus.append(block);
        });

        msgStatus.addClass("row");
    }

    $(".pair").each(function (id, el) {
        $(el).click(function () {
            $(form['day']).val($(el).data('day'));
            $(form['pair']).val($(el).data('pair'));
        });
    });
})();

// Espera a que el documento HTML se haya cargado completamente
$(document).ready(function () {
    // Variables para almacenar las fechas seleccionadas en el calendario
    var fechaInicioSeleccionada;
    var fechaFinSeleccionada;

    // Inicializa el calendario utilizando el plugin FullCalendar
    $('#calendario').fullCalendar({
        // Configuración de la barra de encabezado del calendario
        header: {
            left: 'prevYear,prev,next,nextYear today', // Botones de navegación
            center: 'title', // Título del calendario en el centro
            right: '' // Sección de encabezado derecha vacía
        },
        // Permite la selección de fechas en el calendario
        selectable: true,
        // Función que se ejecuta al seleccionar un rango de fechas
        select: function (start, end, jsEvent, view) {
            // Si es un rango de un solo día, ajusta la fecha de fin para que sea igual a la fecha de inicio
            fechaFinSeleccionada = start.isSame(end, 'day') ? moment(start).endOf('day') : end;

            // Actualiza los campos de inicio y fin en el formulario
            $('#fechaInicio').val(start.format());
            $('#fechaFin').val(fechaFinSeleccionada.format());

            // Muestra el formulario
            $('form').show();
        },
        // Función que se ejecuta al hacer clic en un día del calendario
        dayClick: function (date, jsEvent, view) {
            // Establece tanto la fecha de inicio como la de fin al hacer clic en un solo día
            fechaInicioSeleccionada = date.format();
            fechaFinSeleccionada = date.format();

            // Actualiza los campos de inicio y fin en el formulario
            $('#fechaInicio').val(fechaInicioSeleccionada);
            $('#fechaFin').val(fechaFinSeleccionada);

            // Muestra el formulario
            $('form').show();
        },
        // Función que se ejecuta al renderizar un día del calendario
        dayRender: function (date, cell) {
            // Deshabilita los días pasados
            var today = moment();
            if (date.isBefore(today, 'day')) {
                cell.css('background-color', '#f2f2f2'); // Cambia el color de fondo de los días pasados
                cell.addClass('fc-disabled-day'); // Agrega una clase para deshabilitar el clic
            }
        },
        // Función que especifica el rango de fechas válido
        validRange: function (nowDate) {
            // Deshabilita las fechas pasadas
            return {
                start: nowDate
            };
        },
        // Función que se ejecuta al renderizar la vista del calendario
        viewRender: function (view, element) {
            // Agrega clases de Bootstrap a los botones de navegación y estilos adicionales
                    $('.fc-prevYear-button, .fc-prev-button, .fc-next-button, .fc-nextYear-button, .fc-today-button')
                        .addClass('btn btn-primary')
                        .css({
                            'margin': '1rem', // Ajusta los valores de padding según tus necesidades
                            'font-size': '1rem' // Ajusta el tamaño de fuente según tus necesidades
                        });

            // Obtiene la fecha actual
            var today = moment();
            var currentYear = today.year();

            // Deshabilita el botón de año anterior si estamos en el año actual
            if (view.intervalEnd.year() === currentYear) {
                $('.fc-prevYear-button').prop('disabled', true).addClass('fc-state-disabled');
            } else {
                $('.fc-prevYear-button').prop('disabled', false).removeClass('fc-state-disabled');
            }
        },
        // Configuración de idioma y botones de texto
        locale: 'es',
        buttonText: {
            today: 'HOY',
            month: 'mes',
            prev: '<<',
            next: '>>',
            prevYear: 'Año anterior',
            nextYear: 'Próximo año'
        },
        // Configuración adicional para el calendario
        firstDay: 1,
        dayNamesShort: ['Dom', 'Lun', 'Mar', 'Mié', 'Jue', 'Vie', 'Sáb'],
        dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
        monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
        monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic']
    });
});